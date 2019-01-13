package com.zjcds.czt.controller;

import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.ExamQuestionForm;
import com.zjcds.czt.domain.dto.ExamResultForm;
import com.zjcds.czt.service.HighExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
@JsonViewException
@RestController
@RequestMapping("/highExams")
@Api(description = "高新评测操作")
public class HighExamController {

    @Autowired
    private HighExamService highExamService;

    @GetMapping("/basic/questions")
    @ApiOperation(value = "查询基础评测所有问题", produces = "application/json;charset=utf-8")
    public ResponseResult<List<ExamQuestionForm.Owner>> queryBasicExamQuestions() {
        return new ResponseResult<>(highExamService.queryBasicModuleQuestions());
    }

    @PostMapping("/basic/answers")
    @ApiOperation(value = "提交基础评测答案", produces = "application/json;charset=utf-8")
    public ResponseResult<ExamResultForm.BasicExamResult> answerBasicExam(@RequestBody List<ExamQuestionForm.Answer> answers) {
        return new ResponseResult<>(highExamService.answerBasicExam(answers));
    }

    @PostMapping("/high/answers")
    @ApiOperation(value = "提交高新评分答案", produces = "application/json;charset=utf-8")
    public ResponseResult<ExamResultForm.HighExamResult> answerHighExam(@RequestBody List<ExamQuestionForm.Answer> answers) {
        return new ResponseResult<>(highExamService.answerHighExam(answers));
    }

    @PostMapping("/high/detailResult")
    @ApiOperation(value = "发送评测报告", produces = "application/json;charset=utf-8")
    public ResponseResult<Void> sendDetailResult(@RequestBody ExamResultForm.SendDetailResult form) {
        highExamService.sendDetailResult(form.getCompanyName(), form.getTelephone());
        return new ResponseResult<>(null);
    }

    @GetMapping("/high/questions")
    @ApiOperation(value = "查询高新评分所有问题", produces = "application/json;charset=utf-8")
    public ResponseResult<List<ExamQuestionForm.Owner>> queryHighExamQuestions() {
        return new ResponseResult<>(highExamService.queryHighModuleQuestions());
    }

}
