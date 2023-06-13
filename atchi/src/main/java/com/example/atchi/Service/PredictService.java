package com.example.atchi.Service;

import com.example.atchi.Dto.PredictListResultDto;
import com.example.atchi.Dto.PredictResponseDto;
import com.example.atchi.Dto.PredictResultDto;
import com.example.atchi.Entity.MMSEEntity;
import com.example.atchi.Entity.PredictEntity;
import com.example.atchi.Entity.lifePatternEntity;
import com.example.atchi.Repository.LifePatternRepository;
import com.example.atchi.Repository.MMSERepository;
import com.example.atchi.Repository.PredictRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PredictService {
    private final LifePatternRepository lifePatternRepository;
    private final PredictRepository predictRepository;
    private final MMSERepository mmseRepository;

    public PredictService(LifePatternRepository lifePatternRepository, PredictRepository predictRepository, MMSERepository mmseRepository) {
        this.lifePatternRepository = lifePatternRepository;
        this.predictRepository = predictRepository;
        this.mmseRepository = mmseRepository;
    }

    public PredictResultDto predictModule(int mid){

        PredictResultDto response = new PredictResultDto();
        Date startDate;
        Date endDate;
        //lifePattern 가져오기
        List<lifePatternEntity> lifePatternEntities = lifePatternRepository.findLifePatternAllByMid(mid);
        Boolean lifePatternCount = false;
        Boolean MMSECount = false;
        List<ArrayList<Double>> lifePatternList = new ArrayList<>();
        if(lifePatternEntities.size()>=120){
            for(int i=0;i<120;i++){
                lifePatternEntity lifePattern = lifePatternEntities.get(i);
                ArrayList<Double> oneDayLifePattern = new ArrayList<>();
                oneDayLifePattern.add((double)lifePattern.getActivitySteps());
                oneDayLifePattern.add((double)lifePattern.getSleepDuration());
                oneDayLifePattern.add((double)lifePattern.getSleepHrAverage());
                oneDayLifePattern.add((double)lifePattern.getSleepRmssd());
                lifePatternList.add(oneDayLifePattern);
            }
            lifePatternCount = true;


        }
        //mmse 가져오기
        MMSEEntity mmseEntity = mmseRepository.findMMSEByMid(mid);
        List<Integer> MMSEAnswer = mmseEntity.getQuestions();
        if(MMSEAnswer.size()==19){
            MMSECount = true;
        }
        System.out.println("lifePattern : "+lifePatternEntities.size());
        System.out.println("mmse : "+MMSEAnswer.size());
        if(MMSECount&lifePatternCount){
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
            headers.setContentType(mediaType);
            //
            PredictResponseDto predictResponseDto = new PredictResponseDto();
            predictResponseDto.setMMSEList(MMSEAnswer);
            predictResponseDto.setLifePatternList(lifePatternList);
            HttpEntity<PredictResponseDto> entity = new HttpEntity<>(predictResponseDto, headers);
            String url = "http://203.255.3.48:1225/predict";
            ArrayList<Double> proba = restTemplate.postForObject(url, entity, ArrayList.class);
            startDate = lifePatternEntities.get(lifePatternEntities.size()-1).getDate();
            endDate = lifePatternEntities.get(0).getDate();
            response.setStartDate(startDate);
            response.setEndDAte(endDate);
            response.setPredictResult(proba);
            PredictEntity predictEntity = PredictEntity.builder().mid(mid)
                    .startDate(startDate)
                    .endDate(endDate)
                    .notDementia(proba.get(0))
                    .beforeDementia(proba.get(1))
                    .Dementia(proba.get(2))
                    .build();
            predictRepository.save(predictEntity);

            System.out.println(response);
        }
        //요청하기
        return response;



    }
    public List<PredictListResultDto> getPredictList(Integer mid){
        List<PredictEntity> predictEntities = predictRepository.findAllByMid(mid);
        List<PredictListResultDto> result = new ArrayList<>();
        for(int i = 0 ; i<predictEntities.size();i++){
            PredictListResultDto predictDto = new PredictListResultDto();
            PredictEntity predictEntity = predictEntities.get(i);
            predictDto.setPid(predictEntity.getId());
            predictDto.setMid(predictEntity.getMid());
            predictDto.setDementia(predictEntity.getDementia());
            predictDto.setBeforeDementia(predictEntity.getBeforeDementia());
            predictDto.setNotDementia(predictEntity.getNotDementia());
            predictDto.setStartDate(predictEntity.getStartDate());
            predictDto.setEndDate(predictEntity.getEndDate());
            result.add(predictDto);
        }
        return result;
    }

}
