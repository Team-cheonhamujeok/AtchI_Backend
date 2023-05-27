package com.example.atchi.Service;

import com.example.atchi.Dto.MMSEListResultDto;
import com.example.atchi.Dto.MMSERequestDto;
import com.example.atchi.Dto.MMSEResultDto;
import com.example.atchi.Entity.MMSEEntity;
import com.example.atchi.Repository.MMSERepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MMSEService {
    private final MMSERepository mmseRepository;

    public MMSEService(MMSERepository mmseRepository) {
        this.mmseRepository = mmseRepository;
    }


    public void saveMMSE(MMSERequestDto answer){
        //유효한 mid인지 확인하는 api가 필요
        MMSEEntity mmseEntity = new MMSEEntity();
        mmseEntity.setMid(answer.getMid());
        mmseEntity.setDate(answer.getDate());
        List<String>  questions = answer.getQuestions();
        mmseEntity.setQuestions(questions);
        Integer sumValue = 0;
        for(int i = 0; i<answer.getQuestions().size();i++){
            String num = questions.get(i);
            if(num.equals("2")){
                sumValue++;
            }
        }
        mmseEntity.setReulst(sumValue);
        mmseRepository.save(mmseEntity);
    }
    public List<MMSEListResultDto> getMMSEList(Integer mid){
        List<MMSEListResultDto> mmseListResultDtos = new ArrayList<>();
        List<MMSEEntity> MMSEEntityList= mmseRepository.findAllByMid(mid);
        for(int i =0;i<MMSEEntityList.size();i++){
            MMSEEntity mmseEntity = MMSEEntityList.get(i);
            MMSEListResultDto mmseListResultDto = new MMSEListResultDto();
            mmseListResultDto.setMmseid(mmseEntity.getId());
            mmseListResultDto.setDate(mmseEntity.getDate());
            mmseListResultDto.setResult(mmseEntity.getReulst());
            mmseListResultDtos.add(mmseListResultDto);
        }
        return mmseListResultDtos;
    }
    public MMSEResultDto getMMSE(Integer mmseid){

        MMSEResultDto mmseResultDto = new MMSEResultDto();
        try{
            Optional<MMSEEntity> mmseEntity = mmseRepository.findById(mmseid);
            if(mmseEntity.get() != null){
                mmseResultDto.setMid(mmseEntity.get().getMid());
                mmseResultDto.setQuestions(mmseEntity.get().getQuestions());
                mmseResultDto.setDate(mmseEntity.get().getDate());
                mmseResultDto.setResult(mmseEntity.get().getReulst());
                return mmseResultDto;
            }else{ //mmseid에 맞는 mmse가 없을 때
                mmseResultDto.setMid(-1);
                return mmseResultDto;
            }
        }catch (Exception e){
            //db 또는 다른 오류
            mmseResultDto.setMid(-2);
            return mmseResultDto;
        }

    }
}
