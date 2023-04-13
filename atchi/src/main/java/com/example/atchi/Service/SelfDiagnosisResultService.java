package com.example.atchi.Service;

import com.example.atchi.Dto.DiagnosisResponseDto;
import com.example.atchi.Dto.returnDiagnosisDto;
import com.example.atchi.Entity.selfDiagnosisEntity;
import com.example.atchi.Repository.SelfDiagnosisResultRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service("SelfDiagnosisResultService")
public class SelfDiagnosisResultService {
    private final SelfDiagnosisResultRepository selfDiagnosisResultRepository;

    public SelfDiagnosisResultService(SelfDiagnosisResultRepository selfDiagnosisResultRepository) {
        this.selfDiagnosisResultRepository = selfDiagnosisResultRepository;
    }

    public Boolean doDiagnosis(DiagnosisResponseDto diagnosisResult){
        try{
            int resultSum = 0;
            List<Integer> answerList = diagnosisResult.getAnswerlist();
            for(int i = 0;i<answerList.size();i++){
                resultSum += answerList.get(i);
            }
//            resultSum+= diagnosisResult.getAnswer1()+diagnosisResult.getAnswer2()+diagnosisResult.getAnswer3()+diagnosisResult.getAnswer4()
//                    +diagnosisResult.getAnswer5()+diagnosisResult.getAnswer6()+diagnosisResult.getAnswer7()+diagnosisResult.getAnswer8()+
//                    diagnosisResult.getAnswer9()+diagnosisResult.getAnswer10()+diagnosisResult.getAnswer11()+diagnosisResult.getAnswer12()
//                    +diagnosisResult.getAnswer13()+diagnosisResult.getAnswer14()+diagnosisResult.getAnswer15();
            System.out.println("getmid"+diagnosisResult.getMid());
            selfDiagnosisEntity diagnosisEntity = selfDiagnosisEntity.builder()
                    .mid(diagnosisResult.getMid())
                    .selfDiagnosisDate(diagnosisResult.getDate())
                    .result(resultSum)
                    .answer1(answerList.get(0))
                    .answer2(answerList.get(1))
                    .answer3(answerList.get(2))
                    .answer4(answerList.get(3))
                    .answer5(answerList.get(4))
                    .answer6(answerList.get(5))
                    .answer7(answerList.get(6))
                    .answer8(answerList.get(7))
                    .answer9(answerList.get(8))
                    .answer10(answerList.get(9))
                    .answer11(answerList.get(10))
                    .answer12(answerList.get(11))
                    .answer13(answerList.get(12))
                    .answer14(answerList.get(13))
                    .answer15(answerList.get(14)).build();
            selfDiagnosisResultRepository.save(diagnosisEntity);
            return true;
        }catch(Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }
    public List<returnDiagnosisDto> getDiagnosisList(int mId){
        //db에서 찾은 diagnosisList 목록
        List<selfDiagnosisEntity> diagnosisList = selfDiagnosisResultRepository.findByMid(mId);
        System.out.println("list크기");
        System.out.println(diagnosisList.size());
//        List<selfDiagnosisEntity> diagnosisList = new ArrayList<selfDiagnosisEntity>();
        // 반환할 diagnosis 목록
        List<returnDiagnosisDto> returnDiagnosis = new ArrayList<>();
        for(int i =0;i<diagnosisList.size();i++){
            //새로 정보를 담을 returnDto
            returnDiagnosisDto object = new returnDiagnosisDto();
            // 현재 정보를 옮길 entity
            selfDiagnosisEntity diagnosis = diagnosisList.get(i);
            object.setDid(diagnosis.getId());
            object.setMid(diagnosis.getMid());
            //answerList 목록 옮기기
            List<Integer> answerList = new ArrayList<Integer>();
            answerList.add(diagnosis.getAnswer1());
            answerList.add(diagnosis.getAnswer2());
            answerList.add(diagnosis.getAnswer3());
            answerList.add(diagnosis.getAnswer4());
            answerList.add(diagnosis.getAnswer5());
            answerList.add(diagnosis.getAnswer6());
            answerList.add(diagnosis.getAnswer7());
            answerList.add(diagnosis.getAnswer8());
            answerList.add(diagnosis.getAnswer9());
            answerList.add(diagnosis.getAnswer10());
            answerList.add(diagnosis.getAnswer11());
            answerList.add(diagnosis.getAnswer12());
            answerList.add(diagnosis.getAnswer13());
            answerList.add(diagnosis.getAnswer14());
            answerList.add(diagnosis.getAnswer15());
            object.setAnswerlist(answerList);
            //날짜
            object.setDate(diagnosis.getSelfDiagnosisDate());
            //합산결과
            object.setResult(diagnosis.getResult());
            //List
            returnDiagnosis.add(object);
        }
        return returnDiagnosis;
    }
}