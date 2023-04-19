package com.example.atchi.Service;

import com.example.atchi.Dto.loginResponseDto;
import com.example.atchi.Dto.logoutResponseDto;
import com.example.atchi.Dto.memberResponseDto;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service("MemberService")
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원등록
    public String register(memberResponseDto member){
        List<MemberEntity> findMembers = memberRepository.findByEmail(member.getEmail());
//        //멤버 중복 여부
//        //중복된 멤버 없음
        if(findMembers.size() <=0){
            try{
                MemberEntity memberEntity = MemberEntity.builder()
                        .name(member.getName())
                        .birthday(member.getBirthday())
                        .email(member.getEmail())
                        .gender(member.getGender())
                        .pw(member.getPw()).build();
                memberRepository.save(memberEntity);
                return "success";
            }catch(Exception e){
                return "DB err";
            }

        }else{
            //중복된 멤버 있음

            return "duplicate users";

        }
    }
    public Integer login(loginResponseDto loginMember){
       List<MemberEntity> findMember = memberRepository.findByEmail(loginMember.getId());
       if(findMember.size()==1){
           String loginPW = loginMember.getPw();
           String memberPW = findMember.get(0).getPw();
           if (loginPW.equals(memberPW)){
               return findMember.get(0).getId();
           }else{
               //비밀번호 틀림
               return 0;
           }

       }else if (findMember.size() > 1){ //중복된 멤버가 있을 때
           return -1;
       }else if (findMember.size() == 0){ //멤버가 없을 때
           return -2;
       }else{
           return -3;
       }
    }

    public Integer logout(logoutResponseDto logoutMember){
        List<MemberEntity> findMember = memberRepository.findByEmail(logoutMember.getEmail());
        return findMember.size();

    }
}
