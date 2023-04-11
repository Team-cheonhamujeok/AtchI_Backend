package com.example.atchi.Controller;


import com.example.atchi.EmailService;
import com.example.atchi.Model.mainConfirmResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final EmailService emailService;

    @GetMapping("/mailConfirm")
    @ResponseBody
    public mainConfirmResult mailConfirm(@RequestParam String email) throws Exception {
        String code2 = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code2);
        String code = emailService.createKey();
        log.info("인증코드 : " + code);
        mainConfirmResult certificationNumber = new mainConfirmResult();
        certificationNumber.setCertificationNumber(code);
        return certificationNumber ;
    }
}
