package com.example.atchi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeekQuizResponseDto {
    private int wqid;
    private int mid;
    private Date startDate;
    private Boolean mon;
    private Boolean tue;
    private Boolean wed;
    private Boolean thu;
    private Boolean fri;
    private Boolean sat;
    private Boolean sun;
    public WeekQuizResponseDto(int wqid, int mid, Date startDate, Boolean mon, Boolean tue, Boolean wed, Boolean thu, Boolean fri, Boolean sat,Boolean sun){
        this.wqid = wqid;
        this.mid = mid;
        this.startDate = startDate;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

}
