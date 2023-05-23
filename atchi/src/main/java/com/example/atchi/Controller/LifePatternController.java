package com.example.atchi.Controller;

import com.example.atchi.Dto.lifePatternResponseDto;
import com.example.atchi.Dto.lifePatternResultDto;
import com.example.atchi.Service.LifePatternService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LifePatternController {
    private final LifePatternService lifePatternService;

    public LifePatternController(LifePatternService lifePatternService) {
        this.lifePatternService = lifePatternService;
    }

    @PostMapping("/lifePattern")
    public lifePatternResultDto getLifePattern(@RequestBody ArrayList<lifePatternResponseDto> lifePatternList ){
        for(int i = 0 ; i<lifePatternList.size();i++){
            System.out.println(lifePatternList.get(i).getDate()+"\n");
        }
        lifePatternResultDto resultDto = lifePatternService.saveLifePattern(lifePatternList);
        return resultDto;
    }

}
