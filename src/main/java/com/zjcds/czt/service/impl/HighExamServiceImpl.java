package com.zjcds.czt.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Iterators;
import com.zjcds.czt.dao.jpa.ExamModuleDao;
import com.zjcds.czt.domain.dto.ExamQuestionForm;
import com.zjcds.czt.domain.dto.ExamResultForm;
import com.zjcds.czt.domain.dto.OptionForm;
import com.zjcds.czt.domain.entity.jpa.ExamModule;
import com.zjcds.czt.domain.entity.jpa.ExamQuestion;
import com.zjcds.czt.domain.entity.jpa.ExamRecord;
import com.zjcds.czt.service.HighExamService;
import com.zjcds.czt.utils.ExamQuestionUtils;
import com.zjcds.czt.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
@Service
@Transactional(readOnly = true)
public class HighExamServiceImpl implements HighExamService {

    @Autowired
    private ExamModuleDao examModuleDao;

    @Override
    public List<ExamQuestionForm.Owner> queryBasicModuleQuestions() {
        ExamModule examModule = examModuleDao.findOneForFetchQuestions(1);
        try {
            return ExamQuestionUtils.tranExamQuestions(examModule.getQuestions());
        } catch (Exception e) {
            throw new IllegalStateException("查询基础评测题目异常");
        }
    }

    @Override
    public List<ExamQuestionForm.Owner> queryHighModuleQuestions() {
        ExamModule examModule = examModuleDao.findOneForFetchQuestions(2);
        try {
            return ExamQuestionUtils.tranExamQuestions(examModule.getQuestions());
        } catch (Exception e) {
            throw new IllegalStateException("查询基础评测题目异常");
        }
    }

    @Override
    public ExamResultForm.BasicExamResult answerBasicExam(List<ExamQuestionForm.Answer> answers) {
        ExamModule examModule = examModuleDao.findOneForFetchQuestions(1);
        Double score = 0D;
        for (ExamQuestion examQuestion : examModule.getQuestions()) {
            score += calcScore(examQuestion, answers);
        }
        if (score == 7) {
            return new ExamResultForm.BasicExamResult(true);
        }
        return new ExamResultForm.BasicExamResult(false);
    }

    @Override
    public ExamResultForm.HighExamResult answerHighExam(List<ExamQuestionForm.Answer> answers) {
        return null;
    }

    private Double calcScore(ExamQuestion examQuestion, List<ExamQuestionForm.Answer> answers) {
        ExamQuestionForm.Answer found = Iterators.find(answers.iterator(), answer -> {
            if (answer.getId().equals(examQuestion.getId())) {
                return true;
            } else {
                return false;
            }
        }, null);
        if (found == null) {
            return 0D;
        }
        return calcScore(examQuestion, found);
    }

    private Double calcScore(ExamQuestion examQuestion, ExamQuestionForm.Answer answer) {
        try {
            List<OptionForm.Owner> options = JsonUtils.toObject(examQuestion.getDetails(), new TypeReference<List<OptionForm.Owner>>() {
            });
            OptionForm.Owner found = Iterators.find(options.iterator(), option -> {
                if (answer.getAnswer().equals(option.getId())) {
                    return true;
                } else {
                    return false;
                }
            }, null);
            if (found != null) {
                return found.getScore();
            }
            return 0D;
        } catch (IOException e) {
            return 0D;
        }
    }

    @Override
    public void sendDetailResult(String companyName, String telephone) {

    }
}
