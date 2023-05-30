package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnDto {
    private Boolean success;
    private Object response;
    private String error;
}
