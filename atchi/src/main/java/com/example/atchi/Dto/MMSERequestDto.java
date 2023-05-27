package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MMSERequestDto {
    private int mid;
    private List<String> questions  = new ArrayList<String>();
    private Date date;
}
