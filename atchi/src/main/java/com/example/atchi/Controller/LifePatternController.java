package com.example.atchi.Controller;

import com.example.atchi.Dto.*;
import com.example.atchi.Service.LifePatternService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LifePatternController {
    private final LifePatternService lifePatternService;

    public LifePatternController(LifePatternService lifePatternService) {
        this.lifePatternService = lifePatternService;
    }

    @PostMapping("/lifePattern")
    public ResponseEntity<lifePatternResultDto> saveLifePattern(@RequestBody ArrayList<lifePatternResponseDto> lifePatternList ){

        if(lifePatternList.size() == 0){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        try{
            lifePatternResultDto resultDto = lifePatternService.saveLifePattern(lifePatternList);
            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lifePattern/lastDate")
    public ResponseEntity<ReturnDto>findLifePatternLastDate(@RequestParam Integer mid){
        ReturnDto returnDto = new ReturnDto();
       LastDateResponseDto lastDateResponseDto = lifePatternService.findLifePatternLastDate(mid);
       if(lastDateResponseDto.getLastDate() != null){
           returnDto.setResponse(lastDateResponseDto);
       }else{
           returnDto.setResponse(new EmptyDto());
       }
        returnDto.setSuccess(true);
        returnDto.setError("");
       return new ResponseEntity<ReturnDto>(returnDto, HttpStatus.OK);
    }

}
