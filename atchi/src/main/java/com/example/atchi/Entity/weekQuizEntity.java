package com.example.atchi.Entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Getter
@Setter
@Builder
@Entity(name = "weekQuiz")
//생성자 오류
@AllArgsConstructor
public class weekQuizEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int mid;
    private Date startDate;
    private Boolean mon;
    private Boolean tue;
    private Boolean wed;
    private Boolean thu;
    private Boolean fri;
    private Boolean sat;
    private Boolean sun ;

    public weekQuizEntity() {
        this.mon = Boolean.FALSE;
        this.tue = Boolean.FALSE;
        this.wed = Boolean.FALSE;
        this.thu = Boolean.FALSE;
        this.fri = Boolean.FALSE;
        this.sat = Boolean.FALSE;
        this.sun = Boolean.FALSE;
    }
}
