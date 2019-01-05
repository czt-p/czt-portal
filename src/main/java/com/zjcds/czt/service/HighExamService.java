package com.zjcds.czt.service;

import com.zjcds.czt.domain.dto.ExamQuestionForm;
import com.zjcds.czt.domain.dto.ExamResultForm;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public interface HighExamService {

    public List<ExamQuestionForm.Owner> queryBasicModuleQuestions();

    public List<ExamQuestionForm.Owner> queryHighModuleQuestions();

    public ExamResultForm.BasicExamResult answerBasicExam(List<ExamQuestionForm.Answer> answers);

    public ExamResultForm.HighExamResult answerHighExam(List<ExamQuestionForm.Answer> answers);

    public void sendDetailResult(String companyName, String telephone);

}
