package com.example.atchi.Controller;

import com.example.atchi.Dto.TodayQuizResultDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AiController {
    @GetMapping("/predictionModel")
    public String predictionModel() throws ParseException {
        Double[] arr = {0.58,0.369,6.184,9.176,2.150};
        List<Double[]> list = new ArrayList<>();
        list.add(arr);
        list.add(arr);
//        RestTemplate restTemplate = new RestTemplate(factory);
//        Integer result = restTemplate.postForObject(BASE_URL + "/employee", request, Employee.class);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        HttpEntity<List<Double[]>> entity = new HttpEntity<>(list, headers);
        String url = "http://127.0.0.1:5000";
        String response = restTemplate.postForObject(url, entity, String.class);
        System.out.println(response);
        return "hi";

    }
}
