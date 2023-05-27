package com.example.atchi.Controller;

import com.example.atchi.Dto.*;
import com.example.atchi.Service.MMSEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MMSEController {
    private final MMSEService mmseService;


    @PostMapping("/MMSE")
    public ResponseEntity<ResultDto> saveMMSE(@RequestBody MMSERequestDto answer){
        ResultDto result = new ResultDto();
        try{
            mmseService.saveMMSE(answer);
            result.setMessage("Success save");
            return new ResponseEntity<>(result,HttpStatus.OK);

        }catch (Exception e){
            result.setMessage("DB error");
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/MMSE/getAllMMSE")
    public ResponseEntity<List<MMSEListResultDto>>  getAllMMSE(@RequestParam Integer mid){
        try{
            List<MMSEListResultDto>  result = mmseService.getMMSEList(mid);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/MMSE/getMMSE")
    public ResponseEntity<MMSEResultDto>getMMSE(@RequestParam Integer mmseid){
        try{
            MMSEResultDto mmseResultDto = mmseService.getMMSE(mmseid);
            Integer mid = mmseResultDto.getMid();
            if( mid >=0){
                return new ResponseEntity<>(mmseResultDto,HttpStatus.OK);
            }else if(mid == -1){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
