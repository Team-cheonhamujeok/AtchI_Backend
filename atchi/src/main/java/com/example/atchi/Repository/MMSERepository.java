package com.example.atchi.Repository;

import com.example.atchi.Entity.MMSEEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MMSERepository extends JpaRepository<MMSEEntity,Integer> {
    List<MMSEEntity> findAllByMid(Integer mid);
    Optional<MMSEEntity> findById(Integer mmseid);
    @Query(value = "SELECT * FROM mmse where mmse.mid = :mid order by mmse.date desc limit 1",nativeQuery = true)
    MMSEEntity findMMSEByMid(Integer mid);
}
