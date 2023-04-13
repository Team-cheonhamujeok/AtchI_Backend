
package com.example.atchi.Controller;

import com.example.atchi.Dto.DiagnosisResponseDto;
import com.example.atchi.Dto.returnDiagnosisDto;
import com.example.atchi.Service.SelfDiagnosisResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SelfDiagnosisResultController {
    private final SelfDiagnosisResultService selfDiagnosisResultService;

    public SelfDiagnosisResultController(SelfDiagnosisResultService selfDiagnosisResultService) {
        this.selfDiagnosisResultService = selfDiagnosisResultService;
    }

    @PostMapping("/addDiagnosis")
    public ResponseEntity<String> addDiagnosis(@RequestBody DiagnosisResponseDto diagnosis){
        try{
            System.out.println(diagnosis.getMid());
           Boolean result = selfDiagnosisResultService.doDiagnosis(diagnosis);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println("하하");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getDiagnosisList")
    public ResponseEntity<List<returnDiagnosisDto>> getDiagnosisList(@RequestParam int mId){
        try{
           List<returnDiagnosisDto> diagnosisList =  selfDiagnosisResultService.getDiagnosisList(mId);
            return new ResponseEntity<List<returnDiagnosisDto>>(diagnosisList,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}