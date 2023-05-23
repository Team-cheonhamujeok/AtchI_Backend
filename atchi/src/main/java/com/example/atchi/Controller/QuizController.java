package com.example.atchi.Controller;

import com.example.atchi.Dto.TodayQuizResultDto;
import com.example.atchi.Dto.chckQuizResultDto;
import com.example.atchi.Dto.checkQuizResponseDto;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Model.LogoutResult;
import com.example.atchi.Model.SignupResult;
import com.example.atchi.Repository.MemberRepository;
import com.example.atchi.Repository.QuizRepository;
import com.example.atchi.Service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class QuizController {
    private final QuizService quizService;

    //퀴즈 새로 등록
    @GetMapping("/newTodayQuiz")
    public ResponseEntity<TodayQuizResultDto> TodayQuiz(@RequestParam Integer mid) throws ParseException {
        TodayQuizResultDto questionList = new TodayQuizResultDto();

        try{
            //존재하는 멘버인지 확인
            System.out.println("why1");
            List<MemberEntity> memberList = quizService.findMember(mid);
            System.out.println("why2");
            Integer memberCount = memberList.size();
            System.out.println("why3");
            if (memberCount == 0){
                // 존재하는 멤버가 없을 때
                System.out.println("why4");
                questionList.setMessage("No user exists");
                return new ResponseEntity<>(questionList, HttpStatus.BAD_REQUEST);
            }else if(memberCount == 1){ //멤버 있음
                System.out.println("why5");
                // 당일 등록된 퀴즈 유무 확인
                TodayQuizResultDto findQuiz = quizService.findTodayQuiz(mid);
                //등록된 퀴즈가 없음
                if(findQuiz == null){
                    System.out.println("why6");
                    //퀴즈 생성 및 DB 등록 // 이 함수 안에 퀴즈를 가져옴

                    questionList = quizService.saveTodayQuiz(questionList,mid);
                    System.out.println("why666");
                    if (questionList == null){
                        System.out.println("why66");
                        questionList = new TodayQuizResultDto();
                        questionList.setMessage("Save from DB failed");
                        return new ResponseEntity<>(questionList, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }else{
                    System.out.println("why7");
                    //퀴즈 있음
                    System.out.println(findQuiz.getQuiz1());
                    System.out.println(findQuiz.getQuiz2());
                    System.out.println(findQuiz.getQuiz3());
//                    questionList.setQuiz1(findQuiz.getQuiz1());
//                    questionList.setQuiz2(findQuiz.getQuiz2());
//                    questionList.setQuiz3(findQuiz.getQuiz3());
                    questionList = findQuiz;

                }
                if (questionList.getSolve()!=null){
                    System.out.println("why8");
                    questionList.setMessage("Quiz send success");
                    return new ResponseEntity<>(questionList, HttpStatus.OK);
                }else{
                    questionList.setMessage("Save from DB failed");
                    return new ResponseEntity<>(questionList, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            }else if(memberCount >1){
                // 중복된 멤버가 있음
                questionList.setMessage("There are duplicate users");
                return new ResponseEntity<>(questionList, HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                // 알 수 없는 에러 발생
                questionList.setMessage("unknown error");
                return new ResponseEntity<>(questionList, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //퀴즈 질문지 가져오기

        }catch (Exception e){
            //DB에서 질문 가져오기 실패
            questionList.setMessage("Failed to fetch questions from DB");
            //DB에서 질문 가져오기 실패
            questionList.setMessage("Save from DB failed");
            return new ResponseEntity<>(questionList, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //오류 상황 구체적으로 작성
    @PostMapping("/checkQuiz")
    public ResponseEntity<chckQuizResultDto> checkQuiz(@RequestBody checkQuizResponseDto quizResponseDto){
        String serviceResult = quizService.checkQuiz(quizResponseDto);
        chckQuizResultDto checkQuizResult = new chckQuizResultDto();
        if (serviceResult.equals("성공")){
            checkQuizResult.setMessage("confirmed");
            return new ResponseEntity<>(checkQuizResult,HttpStatus.OK);
        }else{
            checkQuizResult.setMessage(serviceResult);
            return new ResponseEntity<>(checkQuizResult,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
