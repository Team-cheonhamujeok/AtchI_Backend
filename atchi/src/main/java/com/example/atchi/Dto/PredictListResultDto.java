package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PredictListResultDto {
    private int pid;
    private int mid;
    private Date startDate;
    private Date endDate;
    private Double notDementia;
    private Double beforeDementia;
    private Double Dementia;
}
