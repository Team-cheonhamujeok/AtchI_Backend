package com.example.atchi.Controller;

import com.example.atchi.Dto.*;
import com.example.atchi.EmailService;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Model.LoginResult;
import com.example.atchi.Model.LogoutResult;
import com.example.atchi.Model.SignupResult;
import com.example.atchi.Repository.MemberRepository;
import com.example.atchi.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberrepository;
    private final MemberService memberService;
    private final EmailService emailService;
//    public MemberController(MemberRepository memberrepository, MemberService memberService,EmailService emailService) {
//        this.memberrepository = memberrepository;
//        this.memberService = memberService;
//        this.emailService = emailService;
//    }

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
    public ResponseEntity<SignupResult> goSignup(@RequestBody memberResponseDto member){
        SignupResult signupResult = new SignupResult();

        try{
            String result = memberService.register(member);
            if (result.equals("success")) {
                signupResult.setMessage("Member registration complete");
                return new ResponseEntity<>(signupResult,HttpStatus.OK);
            }else if(result.equals("duplicate users")){
                signupResult.setMessage("There are duplicate users");
                return new ResponseEntity<>(signupResult,HttpStatus.OK);
            }else if( result.equals("DB err")){
            signupResult.setMessage("DB save error");
            return new ResponseEntity<>(signupResult,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
                signupResult.setMessage("error");
                return new ResponseEntity<>(signupResult,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch(Exception e){
            signupResult.setMessage(String.valueOf(e));
            return new ResponseEntity<>(signupResult,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResult> goLogin(@RequestBody loginResponseDto loginMem){
        LoginResult loginException = new LoginResult();

        try{
            Integer result =  memberService.login(loginMem);
            LoginResult loginResult = new LoginResult();
            loginResult.setMId(result);
            System.out.println(result);
           if(result >= 0){ //정상적으로 Id값을 가져왔을 때
               // 0이면 -> 비밀번호 틀림, 1이상이면 -> mid 값
               if (result == 0 ){
                   loginResult.setMessage("wrong password");
               }else{
                   loginResult.setMessage("login succeed");
               }
               return ResponseEntity.ok(loginResult);
           }else if(result == -1){ // 중복된 멤버가 있을 때
               loginResult.setMessage("There are duplicate users");
               return ResponseEntity.ok(loginResult);
           }else if (result == -2){ //멤버가 없을 때
               System.out.println(result);
               loginResult.setMessage("No user exists");
               return ResponseEntity.ok(loginResult);
            }else{
               loginResult.setMessage("Unknown error after DB");
               return new ResponseEntity<>(loginResult,HttpStatus.INTERNAL_SERVER_ERROR);
           }
        }catch(Exception e){
            loginException.setMId(null);
            loginException.setMessage(String.valueOf(e));
            return new ResponseEntity<>(loginException,HttpStatus.INTERNAL_SERVER_ERROR);

        }



    }
    //@RequestBody logoutResponseDto logoutMem ResponseEntity<LogoutResult>  new ResponseEntity<>(logoutResult,HttpStatus.OK);
    @PostMapping(value = "/logout")
    public ResponseEntity<LogoutResult> logout(@RequestBody logoutResponseDto logoutMem){
        System.out.println("실실실행");
        LogoutResult logoutResult = new LogoutResult();
        try{
            Integer result = memberService.logout(logoutMem);
            if (result == 0){ // 존재하는 멤버가 없을 때
                logoutResult.setMessage("No user exists");
            }else if(result == 1){ // 로그아웃 성공
                logoutResult.setMessage("logout success");
            }else if(result >1){ // 중복된 멤버가 있음
                logoutResult.setMessage("There are duplicate users");
            }else{ // 알 수 없는 에러 발생
                logoutResult.setMessage("unknown error");
            }
            return new ResponseEntity<>(logoutResult,HttpStatus.OK);
        }catch(Exception e){
            logoutResult.setMessage(String.valueOf(e));
            return new ResponseEntity<>(logoutResult,HttpStatus.INTERNAL_SERVER_ERROR);
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