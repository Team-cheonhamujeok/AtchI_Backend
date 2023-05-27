package com.example.atchi.Repository;

import com.example.atchi.Entity.MMSEEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MMSERepository extends JpaRepository<MMSEEntity,Integer> {
    List<MMSEEntity> findAllByMid(Integer mid);
    Optional<MMSEEntity> findById(Integer mmseid);
}
