package com.example.atchi.Controller;

import com.example.atchi.Model.MailConfirmResult;
import com.example.atchi.Model.SignupResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.atchi.Service.RegisterMail;

@RestController
public class MailServiceRestController {

    @Autowired
    RegisterMail registerMail;

    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
    @GetMapping(value = "/mailConfirm")
    public ResponseEntity<MailConfirmResult> mailConfirm(@RequestParam(name = "email") String email) throws Exception{
        try{
            String code = registerMail.sendSimpleMessage(email);
            System.out.println("사용자에게 발송한 인증코드 ==> " + code);
            MailConfirmResult mailConfirmResult = new MailConfirmResult();
            mailConfirmResult.setCertificationNumber(code);
            mailConfirmResult.setMessage("Mail send success");
            return new ResponseEntity<>(mailConfirmResult, HttpStatus.OK);
        }catch(Exception e){
            MailConfirmResult mailConfirmResult = new MailConfirmResult();
            mailConfirmResult.setMessage(String.valueOf(e));
            return new ResponseEntity<>(mailConfirmResult,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}