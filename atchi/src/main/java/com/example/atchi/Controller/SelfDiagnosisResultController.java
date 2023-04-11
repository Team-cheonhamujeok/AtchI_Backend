package com.example.atchi.Controller;

import com.example.atchi.Dto.DiagnosisResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SelfDiagnosisResultController {
    @PostMapping("/addDiagnosis")
    public Integer addDiagnosis(@RequestBody DiagnosisResponseDto diagnosis){
        try{

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return 0;
    }
}
