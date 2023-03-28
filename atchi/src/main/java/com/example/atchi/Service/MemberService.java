package com.example.atchi.Service;

import com.example.atchi.Dto.memberResponseDto;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MemberService")
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원등록
    public void register(memberResponseDto member){
        List<MemberEntity> findMembers = memberRepository.findByEmail(member.getEmail());
//        //멤버 중복 여부
//        //중복된 멤버 없음
        if(findMembers.size() <=0){
            MemberEntity memberEntity = MemberEntity.builder()
                    .name(member.getName())
                    .birthday(member.getBirthday())
                    .email(member.getEmail())
                    .gender(member.getGender())
                    .pw(member.getPw()).build();
            memberRepository.save(memberEntity);
        }else{
            //중복된 멤버 있음
            System.out.println("실패");

        }



    }
}
