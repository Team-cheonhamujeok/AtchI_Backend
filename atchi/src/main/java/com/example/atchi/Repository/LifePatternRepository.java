package com.example.atchi.Repository;

import com.example.atchi.Entity.lifePatternEntity;
import com.example.atchi.Entity.questionListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LifePatternRepository extends JpaRepository<lifePatternEntity,Integer> {
    @Query(value = "SELECT * FROM life_pattern where life_pattern.mid = :mid order by life_pattern.date desc limit 1",nativeQuery = true)
    lifePatternEntity findLastDateByMid(Integer mid);
    @Query(value = "SELECT * FROM life_pattern where life_pattern.mid = :mid order by life_pattern.date limit 1",nativeQuery = true)
    lifePatternEntity findFirstByDateByMid(Integer mid);
    @Query(value = "select count(*) from life_pattern where life_pattern.mid = :mid and life_pattern.date >= :startDate ",nativeQuery = true)
    Integer countByMid(Integer mid,Date startDate);
}
