package com.example.atchi.Controller;


import com.example.atchi.Dto.memberResponseDto;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Repository.MemberRepository;
import com.example.atchi.Service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MemberController {
    private final MemberRepository memberrepository;
    private final MemberService memberService;
    public MemberController(MemberRepository memberrepository, MemberService memberService) {
        this.memberrepository = memberrepository;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String getTest(){
        Date today = new Date();
        MemberEntity member = MemberEntity.builder().name("김가은2").birthday(today).email("12548@naver.com").gender(Boolean.FALSE).pw("12345").build();
        MemberEntity member2 = memberrepository.save(member);
        MemberEntity member3 = MemberEntity.builder().name("김가은3").birthday(today).email("12548@naver.com").gender(Boolean.FALSE).pw("12345").build();
        MemberEntity member4 = memberrepository.save(member3);
        return "hello";
    }
    @PostMapping("/signup")
    public String goSignup(@RequestBody memberResponseDto member){
        memberService.register(member);
        return "success";
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