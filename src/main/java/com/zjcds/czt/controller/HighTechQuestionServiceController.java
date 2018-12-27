package com.zjcds.czt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjcds.common.base.domain.page.Paging;
import com.zjcds.common.jpa.PageResult;
import com.zjcds.common.jpa.utils.PageUtils;
import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.HighTechQuestionForm;
import com.zjcds.czt.domain.entity.es.HighTechQuestion;
import com.zjcds.czt.service.HighTechQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * created date：2018-12-24
 * @author niezhegang
 */
@JsonViewException
@RestController
@RequestMapping("/highTechQuestions")
@Api(description = "高新技术问题搜索操作")
public class HighTechQuestionServiceController {
    @Autowired
    private HighTechQuestionService highTechQuestionService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/search")
    @ApiOperation(value ="批量查询HighTechQuestion操作，支持分页查询",produces = "application/json;charset=utf-8")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageIndex" ,value = "分页页码" ,defaultValue = "1",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "limit" ,value = "返回行数",defaultValue = "2147483647",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "primaryKey" ,value = "关键字",example="知识产权 立法",dataType = "String",paramType = "query")})
    public ResponseResult<PageResult<HighTechQuestionForm.Owner>> queryHighTechQuestions(@RequestParam(required = false) String primaryKey,Paging paging) {
        if(paging == null)
            paging = new Paging();
        //搜索关键字
        primaryKey = StringUtils.trimToEmpty(primaryKey);

        PageResult<HighTechQuestion> highTechQuestionPageResult =  highTechQuestionService.queryHighTechQuestion(primaryKey,paging);
        return new ResponseResult(PageUtils.copyPageResult(highTechQuestionPageResult,HighTechQuestionForm.Owner.class));
    }


}
