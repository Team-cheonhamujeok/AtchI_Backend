package com.example.atchi.Dto;

import com.sun.istack.NotNull;
import lombok.Getter;


import java.util.Date;
@Getter
public class memberResponseDto {
    private String email;
    private String pw;
    private Date birthday;
    private Boolean gender;
    private String name ;
}
