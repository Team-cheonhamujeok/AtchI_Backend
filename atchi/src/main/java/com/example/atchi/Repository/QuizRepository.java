package com.example.atchi.Repository;

import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Entity.questionListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<questionListEntity,Integer> {
    @Query(value = "SELECT * FROM question_list order by RAND() limit 1",nativeQuery = true)
    List<questionListEntity> findByCategory(String category);
    questionListEntity findById(int id);
}
