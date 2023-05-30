package com.example.atchi.Repository;

import com.example.atchi.Entity.weekQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface WeekQuizRepository extends JpaRepository<weekQuizEntity,Integer> {
    @Transactional
    @Query(value = "SELECT * FROM week_quiz where week_quiz.mid = :mid and week_quiz.start_date = :startDate  ",nativeQuery = true)
    Optional<weekQuizEntity> findweekQuizByMidAndDate(int mid, Date startDate);

    @Transactional
    @Modifying
    @Query(value = "UPDATE week_quiz SET week_quiz.mon = :mon,week_quiz.tue = :tue,week_quiz.wed = :wed,week_quiz.thu = :thu,week_quiz.fri = :fri,week_quiz.sat = :sat,week_quiz.sun = :sun WHERE week_quiz.id = :wqid",nativeQuery = true)
    void updateWeekQuiz(Integer wqid ,Boolean mon,Boolean tue,Boolean wed,Boolean thu,Boolean fri,Boolean sat,Boolean sun);

}
