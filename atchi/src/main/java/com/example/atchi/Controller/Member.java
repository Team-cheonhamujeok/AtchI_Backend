package com.example.atchi.Controller;


import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Member {
    private final MemberRepository memberrepository;

    public Member(MemberRepository memberrepository) {
        this.memberrepository = memberrepository;
    }

    @GetMapping("/")
    public String getTest(){
        Date today = new Date();
        MemberEntity member = MemberEntity.builder().name("김가은").birthday(today).email("12548@naver.com").gender(Boolean.FALSE).pw("12345").id(0).build();
        MemberEntity member2 = memberrepository.save(member);
        return "hello";
    }
}
//    @Id
//    @NotNull
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @NotNull
//    private String email;
//    @NotNull
//    private String pw;
//    private Date birthday;
//    private Boolean gender;
//    private String name ;