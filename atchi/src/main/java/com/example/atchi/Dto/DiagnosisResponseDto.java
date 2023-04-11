package com.example.atchi.Dto;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class DiagnosisResponseDto {
    private Integer mId ;
    private List<Integer> answerLsit ;
    private Date date;
}
