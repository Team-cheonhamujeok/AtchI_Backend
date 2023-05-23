package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TodayQuizResultDto {
    private String message;
    private Integer tqid;
    private String  quiz1;
    private Integer  quiz1Id;
    private Boolean  quiz1Check;
    private String  quiz2;
    private Integer  quiz2Id;
    private Boolean  quiz2Check;
    private String  quiz3;
    private Integer  quiz3Id;
    private Boolean  quiz3Check;
    private Boolean solve;
    private Date quizdate;
}
