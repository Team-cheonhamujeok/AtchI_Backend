package com.example.atchi.Service;

import com.example.atchi.Dto.DiagnosisResponseDto;
import com.example.atchi.Entity.MemberEntity;
import com.example.atchi.Entity.selfDiagnosisEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service("SelfDiagnosisResultService")
public class SelfDiagnosisResultService {
    public Integer doDiagnosis(DiagnosisResponseDto diagnosisResult){
        try{
            List<Integer> answerList = diagnosisResult.getAnswerLsit();
//            selfDiagnosisEntity diagnosisEntity = selfDiagnosisEntity.builder()
//                    .mId(diagnosisResult.getMId())
//                    .selfDiagnosisDate(diagnosisResult.getDate())
//                    .answer1(answerList.get(0))
//                    .answer2(answerList.get(1))
//                    .answer3(answerList.get(2))
//                    .answer4(answerList.get(3))
//                    .answer5(answerList.get(4))
//                    .answer6(answerList.get(5))
//                    .answer7(answerList.get(6))
//                    .answer8(answerList.get(7))
//                    .answer9(answerList.get(8))
//                    .answer10(answerList.get(9))
//                    .answer11(answerList.get(10))
//                    .answer12(answerList.get(11))
//                    .answer13(answerList.get(12))
//                    .answer14(answerList.get(13))
//                    .answer15(answerList.get(14)).build();
//            memberRepository.save(memberEntity);

        }catch(Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return 0;
    }
}
