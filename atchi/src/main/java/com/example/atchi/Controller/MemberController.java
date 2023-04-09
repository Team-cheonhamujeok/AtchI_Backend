package com.example.atchi.Controller;

import com.example.atchi.Dto.*;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Model.LoginResult;
import com.example.atchi.Repository.MemberRepository;
import com.example.atchi.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<String> goSignup(@RequestBody memberResponseDto member){
        try{
            memberService.register(member);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResult> goLogin(@RequestBody loginResponseDto loginMem){
        try{
            Integer result =  memberService.login(loginMem);
            LoginResult loginResult = new LoginResult();
            loginResult.setMId(result);
            System.out.println(result);
           if(result >= 0){ //정상적으로 Id값을 가져왔을 때
               // 0이면 -> 비밀번호 틀림, 1이상이면 -> mid 값
               return ResponseEntity.ok(loginResult);
           }else if(result == -1){ // 중복된 멤버가 있을 때
               return new ResponseEntity<>(loginResult,HttpStatus.NOT_FOUND);
           }else if (result == -2){ //멤버가 없을 때
               System.out.println(result);
               return new ResponseEntity<>(loginResult,HttpStatus.NOT_FOUND);
            }else{

               return new ResponseEntity<>(loginResult,HttpStatus.NOT_FOUND);
           }
        }catch(Exception e){
            System.out.println(9);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }



    }
    public ResponseEntity<String> logout(@RequestBody logoutResponseDto logoutMem){
        try{

            return (ResponseEntity<String>) ResponseEntity.ok();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
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