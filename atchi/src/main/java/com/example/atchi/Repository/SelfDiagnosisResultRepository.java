package com.example.atchi.Repository;

import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Entity.selfDiagnosisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelfDiagnosisResultRepository extends JpaRepository<selfDiagnosisEntity,Integer> {
    List<selfDiagnosisEntity> findByMid(int mid);
}
