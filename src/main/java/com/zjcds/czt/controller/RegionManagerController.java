package com.zjcds.czt.controller;

import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.RegionForm;
import com.zjcds.czt.service.RegionService;
import com.zjcds.czt.utils.RegionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luokp on 2018/12/22.
 */
@JsonViewException
@RestController
@RequestMapping("/regions")
@Api(description = "行政地区管理类操作")
public class RegionManagerController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有菜单项，以列表方式进行组织", produces = "application/json;charset=utf-8")
    public ResponseResult<List<RegionForm.Owner>> queryAllMenuForList() {
        return new ResponseResult<>(regionService.queryAllRegions());
    }

    @GetMapping("/cascade")
    @ApiOperation(value = "查询所有菜单项，以层级方式进行组织", produces = "application/json;charset=utf-8")
    public ResponseResult<List<RegionForm.Cascade>> queryAllMenuForCascade() {
        List<RegionForm.Owner> regions = regionService.queryAllRegions();
        return new ResponseResult<>(RegionUtils.transformToCascade(regions));
    }

}
