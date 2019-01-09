package com.zjcds.czt.controller;

import com.zjcds.common.base.domain.page.Paging;
import com.zjcds.common.dozer.BeanPropertyCopyUtils;
import com.zjcds.common.jpa.PageResult;
import com.zjcds.common.jpa.utils.PageUtils;
import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.SubsidyPolicyForm;
import com.zjcds.czt.service.SubsidyPolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * created date：2019-01-06
 *
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
    @ApiOperation(value = "根据企业全名查询其能享受的资助政策", produces = "application/json;charset=utf-8")
    public ResponseResult<List<SubsidyPolicyForm.Owner>> querySubsidyPolicyByCompanyName(@PathVariable("companyName") String companyName) {
        Assert.hasText(companyName, "输入的企业名称不能为空！");
        return new ResponseResult<>(BeanPropertyCopyUtils.copy(subsidyPolicyService.querySubsidyPolicyByCompanyName(companyName), SubsidyPolicyForm.Owner.class));
    }

    @GetMapping("/region/{regionCode}")
    @ApiOperation(value = "根据区域代码查询区域资助政策", produces = "application/json;charset=utf-8")
    public ResponseResult<List<SubsidyPolicyForm.Owner>> querySubsidyPolicyByRegionCode(@PathVariable("regionCode") String regionCode) {
        Assert.hasText(regionCode, "输入的区域代码不能为空！");
        return new ResponseResult<>(BeanPropertyCopyUtils.copy(subsidyPolicyService.queryByRegion(regionCode), SubsidyPolicyForm.Owner.class));
    }

    @GetMapping
    @ApiOperation(value = "批量查询资助政策操作，支持分页查询", produces = "application/json;charset=utf-8")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageIndex", value = "分页页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "返回行数", defaultValue = "2147483647", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "queryString", value = "查询条件", defaultValue = "field~Eq~1234", dataType = "String", paramType = "query", allowMultiple = true),
            @ApiImplicitParam(name = "orderBy", value = "排序", defaultValue = "field1Desc", dataType = "String", paramType = "query", allowMultiple = true)})
    public ResponseResult<PageResult<SubsidyPolicyForm.Owner>> querySubsidyPolicies(Paging paging,
                                                                                    @RequestParam(required = false, name = "queryString") List<String> queryString,
                                                                                    @RequestParam(required = false, name = "orderBy") List<String> orderBys) {
        if (CollectionUtils.isNotEmpty(queryString)) {
            for (String query : queryString) {
                String queryUpper = StringUtils.upperCase(query);
                if (queryUpper.startsWith("REGIONCODE~LIKE~")) {
                    if (query.endsWith("0000%")) {
                        queryString.remove(query);
                        queryString.add(query.replaceFirst("0000%", "%"));
                    } else if (query.endsWith("00%")) {
                        queryString.remove(query);
                        queryString.add(query.replaceFirst("00%", "%"));
                    }
                }
            }
        }
        if (CollectionUtils.isEmpty(orderBys)) {
            orderBys = new ArrayList<>();
            orderBys.add("modifyTimeDesc");
        }
        return new ResponseResult<>(PageUtils.copyPageResult(subsidyPolicyService.querySubsidyPolicies(paging, queryString, orderBys), SubsidyPolicyForm.Owner.class));
    }

    @GetMapping("/recentModify")
    @ApiOperation(value = "查询最新更新的10条资助政策", produces = "application/json;charset=utf-8")
    public ResponseResult<List<SubsidyPolicyForm.Owner>> queryRecentModifySubsidyPolicies() {
        return new ResponseResult<>(BeanPropertyCopyUtils.copy(subsidyPolicyService.queryRecent10UpdateSubsidyPolicy(), SubsidyPolicyForm.Owner.class));
    }

}
