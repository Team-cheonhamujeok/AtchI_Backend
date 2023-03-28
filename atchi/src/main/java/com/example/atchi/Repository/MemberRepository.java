package com.example.atchi.Repository;

import com.example.atchi.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository를 상속받는 MemberRepository, jpaRepository<Entity클래스,PK타입>
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
    List<MemberEntity> findByEmail(String email);
}
