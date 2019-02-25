package com.zjcds.czt.controller;

import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.account.CostAccountForm;
import com.zjcds.czt.service.CostAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author luokp on 2019/1/2.
 */
@JsonViewException
@RestController
@RequestMapping("/costAccount")
@Api(description = "成本核算操作")
public class CostAccountController {

    @Autowired
    private CostAccountService costAccountService;

    @PostMapping
    @ApiOperation(value = "成本核算", produces = "application/json;charset=utf-8")
    public ResponseResult<Void> costAccount(@Valid @RequestBody CostAccountForm costAccount) {
        costAccountService.costAccount(costAccount);
        return new ResponseResult<>(null);
    }

}
