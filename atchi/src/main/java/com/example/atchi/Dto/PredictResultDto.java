package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class PredictResultDto {
    List<Double> predictResult;
    Date startDate;
    Date endDAte;
}
