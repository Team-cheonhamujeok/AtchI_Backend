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
@Entity(name="selfDiagnosisResult")
public class selfDiagnosisEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private int mId;
    @NotNull
    private Date selfDiagnosisDate;
    @NotNull
    private int result;
    @NotNull
    private int answer1;
    @NotNull
    private int answer2;
    @NotNull
    private int answer3;
    @NotNull
    private int answer4;
    @NotNull
    private int answer5;
    @NotNull
    private int answer6;
    @NotNull
    private int answer7;
    @NotNull
    private int answer8;
    @NotNull
    private int answer9;
    @NotNull
    private int answer10;
    @NotNull
    private int answer11;
    @NotNull
    private int answer12;
    @NotNull
    private int answer13;
    @NotNull
    private int answer14;
    @NotNull
    private int answer15;


}
