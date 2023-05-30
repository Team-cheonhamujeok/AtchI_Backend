package com.example.atchi.Repository;

import com.example.atchi.Entity.todayQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodayQuizRepository extends JpaRepository<todayQuizEntity,Integer> {
    List<todayQuizEntity> findByMidAndQuizdate(Integer mid,Date quizdate);
    List<todayQuizEntity> findByQuizdate(Date quizdate);
    Optional<todayQuizEntity> findById(Integer id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE today_quiz SET today_quiz.quiz1check = :check WHERE today_quiz.id = :tqid",nativeQuery = true)
    void updateQuiz1Check(Integer tqid ,Boolean check);
    @Transactional
    @Modifying
    @Query(value = "UPDATE today_quiz SET today_quiz.quiz2check = :check WHERE today_quiz.id = :tqid",nativeQuery = true)
    void updateQuiz2Check(Integer tqid ,Boolean check);
    @Transactional
    @Modifying
    @Query(value = "UPDATE today_quiz SET today_quiz.quiz3check = :check WHERE today_quiz.id = :tqid",nativeQuery = true)
    void updateQuiz3Check(Integer tqid ,Boolean check);
    List<todayQuizEntity> findAll();
    @Transactional
    @Modifying
    @Query(value = "UPDATE today_quiz SET today_quiz.solve = :check WHERE today_quiz.id = :tqid",nativeQuery = true)
    void updateSolveCheck(Integer tqid ,Boolean check);



}
