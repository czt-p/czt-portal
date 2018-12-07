package com.zjcds.czt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 认证相关功能控制器
 * 包括login、logout等
 *
 * @author niezhegang
 */
@Controller
public class AuthenticationController {

    /**
     * 登录成功后默认跳转首页
     *
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index() {
        return "pages/index";
    }

}
