package com.zjcds.czt.controller;

import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.enums.*;
import com.zjcds.czt.service.EnumValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author luokp on 2019/1/11.
 */
@RestController
@RequestMapping("/dicts")
@Api(description = "字典查询操作")
public class DictController {

    @Autowired
    private EnumValueService enumValueService;

    @GetMapping("/financialGrowth")
    @ApiOperation(value = "财务增长情况", produces = "application/json;charset=utf-8")
    public ResponseResult<List<Map<Object, Object>>> financialGrowth() {
        return new ResponseResult<>(enumValueService.toList(FinancialGrowth.class));
    }

    @GetMapping("/highField")
    @ApiOperation(value = "高新领域", produces = "application/json;charset=utf-8")
    public ResponseResult<List<Map<Object, Object>>> highField() {
        return new ResponseResult<>(enumValueService.toList(HighField.class));
    }

    @GetMapping("/ipType")
    @ApiOperation(value = "知识产权类型", produces = "application/json;charset=utf-8")
    public ResponseResult<List<Map<Object, Object>>> ipType() {
        return new ResponseResult<>(enumValueService.toList(IPType.class));
    }

    @GetMapping("/otherCostType")
    @ApiOperation(value = "其他费用类型", produces = "application/json;charset=utf-8")
    public ResponseResult<List<Map<Object, Object>>> otherCostType() {
        return new ResponseResult<>(enumValueService.toList(OtherCostType.class));
    }

    @GetMapping("/recentYearIncoming")
    @ApiOperation(value = "最近一年营业收入", produces = "application/json;charset=utf-8")
    public ResponseResult<List<Map<Object, Object>>> recentYearIncoming() {
        return new ResponseResult<>(enumValueService.toList(RecentYearIncoming.class));
    }

}
