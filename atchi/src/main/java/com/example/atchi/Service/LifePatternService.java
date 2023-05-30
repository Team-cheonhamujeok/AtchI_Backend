package com.example.atchi.Service;

import com.example.atchi.Dto.LastDateResponseDto;
import com.example.atchi.Dto.lifePatternResponseDto;
import com.example.atchi.Dto.lifePatternResultDto;
import com.example.atchi.Entity.lifePatternEntity;
import com.example.atchi.Repository.LifePatternRepository;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("LifePatternService")
public class LifePatternService {
    private final LifePatternRepository lifePatternRepository;

    public LifePatternService(LifePatternRepository lifePatternRepository) {
        this.lifePatternRepository = lifePatternRepository;
    }
    // 다음날 구하기
    private java.util.Date getNextDay(java.util.Date today){
        Calendar cal=Calendar.getInstance();

        cal.setTime(today);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();

    }


    public lifePatternResultDto saveLifePattern(ArrayList<lifePatternResponseDto> lifePatternList){
        List<lifePatternEntity> lifePatternEntityList = new ArrayList<>();
        //현재 입력 받은 lifePattern 날짜와 db에 저장된 날짜 사이에 빈 날들 채우기
        lifePatternEntity lastLpEntity = lifePatternRepository.findLastDateByMid(lifePatternList.get(0).getMid());
        Date lastDate = lifePatternList.get(0).getDate();
        if(lastLpEntity != null){
            System.out.println("이전 데이터 날짜 : "+lastLpEntity);
            Date lastEntityDate = lastLpEntity.getDate();
            long diffDate = (lastDate.getTime()-lastEntityDate.getTime())/86400000;
            for(int j = 1 ; j <diffDate;j++){
                lastEntityDate = getNextDay(lastEntityDate);
//               //entity 생성
                lifePatternEntity lifePatternE = new lifePatternEntity();
                //entity set , 나머지는 NAN
                lifePatternE.setMid(lastLpEntity.getMid());
                lifePatternE.setDate(lastEntityDate);
                lifePatternEntityList.add(lifePatternE);
            }
        }

//        lastDate = lifePatternList.get(0).getDate();
        //현재 입력 받은 lifePatternList 저장
        for(int i = 0;i<lifePatternList.size();i++){

            // lifePattern list에서 하나 꺼내옴
           lifePatternResponseDto getLifePattern = lifePatternList.get(i);
           Date nowDate  = getLifePattern.getDate();
//            System.out.println("today : "+nowDate);
           //비워져있는 날짜
            long diffDate = (nowDate.getTime()-lastDate.getTime())/86400000;
            System.out.println("i : " + i + "diffDate : " + diffDate);
           for(int j = 1 ; j <diffDate;j++){
               lastDate = getNextDay(lastDate);
               System.out.println(lastDate);
//               //entity 생성
               lifePatternEntity lifePatternE = new lifePatternEntity();
               //entity set , 나머지는 NAN
               lifePatternE.setMid(getLifePattern.getMid());
               lifePatternE.setDate(lastDate);
               lifePatternEntityList.add(lifePatternE);
           }

           //저장할 entity
            lifePatternEntity lifePatternE = new lifePatternEntity();
            //entity SET
            lifePatternE.setMid(getLifePattern.getMid());
            lifePatternE.setActivitySteps(getLifePattern.getActivitySteps());
            lifePatternE.setSleepDuration(getLifePattern.getSleepDuration());
            lifePatternE.setSleepHrAverage(getLifePattern.getSleepHrAverage());
            lifePatternE.setSleepRmssd(getLifePattern.getSleepRmssd());
            lifePatternE.setDate(nowDate);
            //entity list에 추가
            lifePatternEntityList.add(lifePatternE);
            //date
            lastDate = getLifePattern.getDate();
        }

        lifePatternRepository.saveAll(lifePatternEntityList);
        lifePatternEntity firstEntity=lifePatternRepository.findFirstByDateByMid(lifePatternList.get(0).getMid());
        Integer lpCount = lifePatternRepository.countByMid(lifePatternList.get(0).getMid(),firstEntity.getDate());
        lifePatternEntity Entity = lifePatternRepository.findLastDateByMid(lifePatternList.get(0).getMid());
        lifePatternResultDto resultDto = new lifePatternResultDto();
        resultDto.setMid(lifePatternList.get(0).getMid());
        resultDto.setLpCount(lpCount);
        resultDto.setLastDate(Entity.getDate());
        resultDto.setPredictStart(lpCount >= 120);
        return resultDto;
    }

    public LastDateResponseDto findLifePatternLastDate(Integer mid){
    Date lastDate = lifePatternRepository.findOnlyLastDateByMid(mid);
    System.out.println("lastDAte : " + lastDate);
    LastDateResponseDto lastDateResponseDto = new LastDateResponseDto();
    lastDateResponseDto.setLastDate(lastDate);
    lastDateResponseDto.setMid(mid);
    return lastDateResponseDto;

    }
}
