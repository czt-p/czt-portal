package com.zjcds.czt.controller;

import com.zjcds.common.jsonview.annotations.JsonViewException;
import com.zjcds.common.jsonview.utils.ResponseResult;
import com.zjcds.czt.domain.dto.SEOConfigForm;
import com.zjcds.czt.service.SEOConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author luokp on 2019/3/12.
 */
@JsonViewException
@RestController
@RequestMapping("/seoConfig")
@Api(description = "SEO设置类操作")
public class SEOConfigController {

    @Autowired
    private SEOConfigService seoConfigService;

    @GetMapping
    @ApiOperation(value ="查询SEO设置信息，不包含角色关联的菜单项",produces = "application/json;charset=utf-8")
    public ResponseResult<SEOConfigForm> getSEOConfig(){
        return new ResponseResult<>(seoConfigService.getSEOConfig());
    }

}
