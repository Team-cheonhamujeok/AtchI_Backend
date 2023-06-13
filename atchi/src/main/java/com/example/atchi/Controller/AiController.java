package com.example.atchi.Controller;

import com.example.atchi.Dto.PredictListResultDto;
import com.example.atchi.Dto.ReturnDto;
import com.example.atchi.Service.PredictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AiController {
    private final PredictService predictService;

    public AiController(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping("/predictionModel")
    @ResponseBody
    public ResponseEntity<ReturnDto>  predictionModel(@RequestParam Integer mid) throws ParseException {
        ReturnDto returnDto = new ReturnDto();
        try{

            List<PredictListResultDto> predictList = predictService.getPredictList(mid);
            returnDto.setResponse(predictList);
            returnDto.setSuccess(true);
            returnDto.setError("");
            return  new ResponseEntity<ReturnDto>(returnDto, HttpStatus.OK);
        }catch (Exception e){
            returnDto.setError(e.getMessage());
            returnDto.setSuccess(false);
            return  new ResponseEntity<ReturnDto>(returnDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
