package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class lifePatternResultDto {
    private int mid;
    private Date lastDate;
    private int lpCount;
    private boolean predictStart;
}
