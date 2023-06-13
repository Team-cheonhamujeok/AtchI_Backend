package com.example.atchi.Repository;

import com.example.atchi.Entity.MMSEEntity;
import com.example.atchi.Entity.PredictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictRepository extends JpaRepository<PredictEntity, Integer> {
    List<PredictEntity> findAllByMid(Integer mid);
}
