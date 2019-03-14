package com.zjcds.czt.controller;

import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.SeoConfigForm;
import com.zjcds.czt.service.SeoConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luokp on 2019/3/12.
 */
@JsonViewException
@RestController
@RequestMapping("/seoConfigs")
@Api(description = "SEO设置类操作")
public class SeoConfigController {

    @Autowired
    private SeoConfigService seoConfigService;

    @GetMapping
    @ApiOperation(value = "查询SEO设置信息", produces = "application/json;charset=utf-8")
    public ResponseResult<List<SeoConfigForm.Owner>> querySeoConfigs() {
        return new ResponseResult<>(seoConfigService.querySeoConfigs());
    }

    @GetMapping("/{pageCode}")
    @ApiOperation(value = "根据页面代码查询SEO设置", produces = "application/json;charset=utf-8")
    public ResponseResult<SeoConfigForm.Owner> querySeoConfigByPageCode(@PathVariable("pageCode") String pageCode) {
        return new ResponseResult<>(seoConfigService.querySeoConfigByPageCode(pageCode));
    }

}
