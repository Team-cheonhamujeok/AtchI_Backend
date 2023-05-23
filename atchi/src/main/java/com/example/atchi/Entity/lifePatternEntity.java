package com.example.atchi.Entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name="lifePattern")
public class lifePatternEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int mid;
    private Date date;
    private int activitySteps; //하루 걸음
    private int sleepDuration; // 총 수면 시간
    private float sleepHrAverage; // 수면 시간 중 평균 심박동
    private float sleepRmssd; //수면 중 심박동 변동-변위 [평균]
}