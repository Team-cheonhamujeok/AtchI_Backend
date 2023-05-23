package com.example.atchi.Service;

import com.example.atchi.Dto.TodayQuizResultDto;
import com.example.atchi.Dto.checkQuizResponseDto;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Entity.questionListEntity;
import com.example.atchi.Entity.todayQuizEntity;
import com.example.atchi.Repository.MemberRepository;
import com.example.atchi.Repository.QuizRepository;
import com.example.atchi.Repository.TodayQuizRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("QuizService")
public class QuizService {

    private final QuizRepository quizRepository;
    private final MemberRepository memberRepository;
    private final TodayQuizRepository todayQuizRepository;
    public QuizService(QuizRepository quizRepository, MemberRepository memberRepository, TodayQuizRepository todayQuizRepository) {
        this.quizRepository = quizRepository;
        this.memberRepository = memberRepository;
        this.todayQuizRepository = todayQuizRepository;
    }

    public List<MemberEntity> findMember(int mId){
//        Optional<MemberEntity> findMember = memberRepository.findById(mId);
        List<MemberEntity> findMember = memberRepository.findById(mId);
        return findMember;
    }

    public TodayQuizResultDto getTodayQuiz(){

        //존재하는 카테고리 목록
        String[] category = {"interpersonalRelationship","spatialPerspective","memory","concentrationOfAttention","understandingAndJudgment","basicKnowledge","life"};
        List<String> categories = List.of(category);
        //랜덤으로 뽑힌 카테고리 목록
        List<String> randomCategoryList = new ArrayList<>();
        while(randomCategoryList.size()<=3){
            //랜덤으로 숫자를 정해서 카테고리를 추출한다.
            double random = Math.random()*100;
            Integer randomNum = (int)random%7;
            System.out.println(randomNum);
            String randomCategory = categories.get(randomNum);
            if(!randomCategoryList.contains(randomCategory)){
                randomCategoryList.add(randomCategory);
            }
        }
        List<TodayQuizResultDto> questionList = new ArrayList<>();
        //정해진 카테고리 3개로 각자 question 1개씩 가져 온다.
        TodayQuizResultDto quizResult = new TodayQuizResultDto();
        for(int i=0;i<=2;i++) {
            questionListEntity question  = getCategoryQustions(randomCategoryList.get(i));
            if(question != null){
                if(i==0){
                    quizResult.setQuiz1(question.getQuestion());
                    quizResult.setQuiz1Id(question.getId());
                    quizResult.setQuiz1Check(quizResult.getQuiz1Check());
                    quizResult.setSolve(quizResult.getSolve());
                    quizResult.setQuizdate(quizResult.getQuizdate());
                }else if(i==1){
                    quizResult.setQuiz2(question.getQuestion());
                    quizResult.setQuiz2Id(question.getId());
                    quizResult.setQuiz2Check(quizResult.getQuiz2Check());

                }else{
                    quizResult.setQuiz3(question.getQuestion());
                    quizResult.setQuiz3Id(question.getId());
                    quizResult.setQuiz3Check(quizResult.getQuiz3Check());
                }
            }

        }
        return quizResult;
        //이제 컨트롤러에서 퀴즈 현황 저장해야함
    }

    public questionListEntity getCategoryQustions(String category){
        List<questionListEntity> categoryQustionList = quizRepository.findByCategory(category);
        if(categoryQustionList.size() == 1){
            questionListEntity question = categoryQustionList.get(0);
            return question;
        }else{
            return null;
        }


    }
    //퀴즈 등록
    public TodayQuizResultDto saveTodayQuiz(TodayQuizResultDto quizResultDto,Integer mId) throws ParseException {
        try{
            // 현재 날짜 구하기
            TimeZone time;
            Date date = new Date();
            DateFormat df = new SimpleDateFormat(
                    "yyyy-MM-dd");
            time = TimeZone.getTimeZone("Asia/Seoul");
            df.setTimeZone(time);
            TodayQuizResultDto quizList = getTodayQuiz();
            System.out.println("퀴즈왜 안뜸? : "+quizList);

            //새로 등록
            todayQuizEntity saveQuiz = todayQuizEntity.builder()
                    .mid(mId)
                    .quiz1id(quizList.getQuiz1Id())
                    .quiz1check(Boolean.FALSE)
                    .quiz2id(quizList.getQuiz2Id())
                    .quiz2check(Boolean.FALSE)
                    .quiz3id(quizList.getQuiz3Id())
                    .quiz3check(Boolean.FALSE)
                    .quizdate(df.parse(df.format(date)))
                    .solve(Boolean.FALSE).build();
            todayQuizRepository.save(saveQuiz);
            //조회해서 tqid 찾기
            TodayQuizResultDto todayQuiz = findTodayQuiz(mId);
            System.out.println("why:"+todayQuiz.getQuiz1());
            //DTO로 변환
            quizResultDto.setQuiz1(quizList.getQuiz1());
            quizResultDto.setQuiz2(quizList.getQuiz2());
            quizResultDto.setQuiz3(quizList.getQuiz3());
            quizResultDto.setQuiz2Id(todayQuiz.getQuiz2Id());
            quizResultDto.setQuiz1Id(todayQuiz.getQuiz1Id());
            quizResultDto.setQuiz3Id(todayQuiz.getQuiz3Id());
            quizResultDto.setQuiz2Check(todayQuiz.getQuiz2Check());
            quizResultDto.setQuiz1Check(todayQuiz.getQuiz1Check());
            quizResultDto.setQuiz3Check(todayQuiz.getQuiz3Check());
            quizResultDto.setTqid(todayQuiz.getTqid());
            quizResultDto.setSolve(todayQuiz.getSolve());
            quizResultDto.setQuizdate(todayQuiz.getQuizdate());
            System.out.println("에러발생 안함");
            return quizResultDto;
        }catch(Exception e){
            System.out.println("에러발생");
            return null;
        }


    }
    //퀴즈 풀면 확인
    public String checkQuiz(checkQuizResponseDto quizResponseDto){
        try{
            int num = quizResponseDto.getQuizNum();
            if(num == 1){
                todayQuizRepository.updateQuiz1Check(quizResponseDto.getTqid(),Boolean.TRUE);
//                todayQuizEntity.builder().id(quizResponseDto.getTqid()).quiz1check(Boolean.TRUE).build();
            }else if(num == 2){
                todayQuizRepository.updateQuiz2Check(quizResponseDto.getTqid(),Boolean.TRUE);
//                todayQuizEntity.builder().id(quizResponseDto.getTqid()).quiz2check(Boolean.TRUE).build();
            }else if(num == 3){
                todayQuizRepository.updateQuiz3Check(quizResponseDto.getTqid(),Boolean.TRUE);
//                todayQuizEntity.builder().id(quizResponseDto.getTqid()).quiz2check(Boolean.TRUE).build();
            }
//            List<todayQuizEntity> selectQuiz = todayQuizRepository.findAll();
            Optional<todayQuizEntity> todayQuiz = todayQuizRepository.findById(quizResponseDto.getTqid());
            int solved = 0;
            todayQuizEntity todayQuizNoneOptional = todayQuiz.get();
            if (todayQuizNoneOptional != null){
                for(int i = 1 ; i<4;i++){
                    if (i == 1 && todayQuizNoneOptional.getQuiz1check()){
                        solved +=1;
                    }else if(i == 2 && todayQuizNoneOptional.getQuiz2check()){
                        solved +=1;
                    }else if(i == 3 && todayQuizNoneOptional.getQuiz2check()){
                        solved +=1;
                    }
                }
                if(solved == 3){
                    todayQuizRepository.updateSolveCheck(quizResponseDto.getTqid(),Boolean.TRUE);
                }
            }else{
                return "No corresponding tqid";
            }

            return "성공";
        }catch(Exception e){
//            throw e;
            return "check error";
        }
    }
    //당일 등록된 퀴즈가 있는지 확인
    public TodayQuizResultDto findTodayQuiz(Integer mId) throws ParseException {
        // 현재 날짜 구하기
        TimeZone time;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(
                "yyyy-MM-dd");
        time = TimeZone.getTimeZone("Asia/Seoul");
        df.setTimeZone(time);
        //퀴즈 찾음
        List<todayQuizEntity> findQuiz= todayQuizRepository.findByMidAndQuizdate(mId,df.parse(df.format(date)));
        System.out.print("date : " + date);
//        List<todayQuizEntity> findQuiz= todayQuizRepository.findByQuizdate(df.parse(df.format(date)));
        System.out.println("사이즈 : "+findQuiz.size());
        for(int i = 0 ; i <findQuiz.size();i++){
            System.out.println("row :" + findQuiz.get(i));
        }
        if(findQuiz.size()==1){
            TodayQuizResultDto quizResultDto =new TodayQuizResultDto();
            todayQuizEntity quiz = findQuiz.get(0);
            quizResultDto.setQuiz1Id(quiz.getQuiz1id());
            quizResultDto.setQuiz2Id(quiz.getQuiz2id());
            quizResultDto.setQuiz3Id(quiz.getQuiz3id());
            quizResultDto.setQuiz1Check(quiz.getQuiz1check());
            quizResultDto.setQuiz2Check(quiz.getQuiz2check());
            quizResultDto.setQuiz3Check(quiz.getQuiz3check());
            quizResultDto.setSolve(quiz.getSolve());
            quizResultDto.setQuizdate(quiz.getQuizdate());
            quizResultDto.setTqid(quiz.getId());
            for(int i = 0 ; i <3;i++){
                if (i == 0){
                    questionListEntity findQuizById = quizRepository.findById(quiz.getQuiz1id());
                    quizResultDto.setQuiz1(findQuizById.getQuestion());
                }else if(i == 1){
                    questionListEntity findQuizById = quizRepository.findById(quiz.getQuiz2id());
                    quizResultDto.setQuiz2(findQuizById.getQuestion());
                } else if(i == 2){
                    questionListEntity findQuizById = quizRepository.findById(quiz.getQuiz3id());
                    quizResultDto.setQuiz3(findQuizById.getQuestion());
                }
            }

            return quizResultDto;

        }else{
            System.out.println("없음");
            return null;
        }
    }
}
