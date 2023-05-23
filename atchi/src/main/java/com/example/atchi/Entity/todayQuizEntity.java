package com.example.atchi.Entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name="todayQuiz")
public class todayQuizEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private int mid;
    private int quiz1id;
    private Boolean quiz1check;
    private int quiz2id;
    private Boolean quiz2check;
    private int quiz3id;
    private Boolean quiz3check;
    private Date quizdate;
    private Boolean solve;


}
