package com.example.atchi.Dto;

import com.example.atchi.Entity.lifePatternEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PredictResponseDto {
    private List<ArrayList<Double>> lifePatternList;
    private List<Integer> MMSEList;
}
