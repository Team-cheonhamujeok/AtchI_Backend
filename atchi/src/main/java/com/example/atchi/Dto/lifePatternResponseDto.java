package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class lifePatternResponseDto {
    private int mid;
    private Date date;
    private int activitySteps; //하루 걸음
    private int sleepDuration; // 총 수면 시간
    private float sleepHrAverage; // 수면 시간 중 평균 심박동
    private float sleepRmssd; //수면 중 심박동 변동-변위 [평균]
}
