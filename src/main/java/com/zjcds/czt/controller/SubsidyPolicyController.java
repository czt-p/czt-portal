package com.zjcds.czt.controller;

import com.zjcds.common.dozer.BeanPropertyCopyUtils;
import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.SubsidyPolicyForm;
import com.zjcds.czt.service.SubsidyPolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created date：2019-01-06
 * @author niezhegang
 */
@JsonViewException
@RestController
@RequestMapping("/subsidyPolicies")
@Api(description = "资助政策查询控制器 ")
public class SubsidyPolicyController {
    @Autowired
    private SubsidyPolicyService subsidyPolicyService;

    @GetMapping("/company/{companyName}")
    @ApiOperation(value ="根据企业全名查询其能享受的资助政策",produces = "application/json;charset=utf-8")
    public ResponseResult<List<SubsidyPolicyForm.Owner>> querySubsidyPolicyByCompanyName(@PathVariable("companyName") String companyName) {
        Assert.hasText(companyName,"输入的企业名称不能为空！");
        return new ResponseResult<>(BeanPropertyCopyUtils.copy(subsidyPolicyService.querySubsidyPolicyByCompanyName(companyName),SubsidyPolicyForm.Owner.class));
    }

    @GetMapping("/region/{regionCode}")
    @ApiOperation(value ="根据区域代码查询区域资助政策",produces = "application/json;charset=utf-8")
    public ResponseResult<List<SubsidyPolicyForm.Owner>> querySubsidyPolicyByRegionCode(@PathVariable("regionCode") String regionCode) {
        Assert.hasText(regionCode,"输入的区域代码不能为空！");
        return new ResponseResult<>(BeanPropertyCopyUtils.copy(subsidyPolicyService.queryByRegion(regionCode),SubsidyPolicyForm.Owner.class));
    }

}
