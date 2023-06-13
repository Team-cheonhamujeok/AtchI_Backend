package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MMSESaveResultDto {
    private String message;
    private List<Double> resultProba;
}
