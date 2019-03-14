package com.zjcds.czt.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luokp on 2019/3/11.
 */
@Getter
@Setter
public class SeoConfigForm {

    @Getter
    @Setter
    @ApiModel(value = "seoConfig", description = "SEO设置对象")
    public static class Owner {
        @ApiModelProperty(value = "页面code", required = true, readOnly = true, example = "home")
        private String pageCode;
        @ApiModelProperty(value = "页面名称", required = true, readOnly = true, example = "主页")
        private String pageName;
        @ApiModelProperty(value = "页面描述", required = true, readOnly = true, example = "主页")
        private String pageDescription;
        @ApiModelProperty(value = "seo title", required = true, example = "策知通")
        private String title;
        @ApiModelProperty(value = "seo meta", required = true)
        private List<Meta> meta;
    }

    @Getter
    @Setter
    @ApiModel(value = "seoConfigUpdate", description = "更新SEO设置对象")
    public static class Update {
        @ApiModelProperty(value = "页面code", required = true, readOnly = true, example = "home")
        private String pageCode;
        @ApiModelProperty(value = "seo title", required = true, example = "策知通")
        private String title;
        @ApiModelProperty(value = "seo meta", required = true)
        private List<Meta> meta;
    }

    @Getter
    @Setter
    @ApiModel(value = "seoConfigUpdateForm", description = "更新SEO设置表单")
    public static class UpdateList {
        @ApiModelProperty(value = "seo config", required = true)
        private List<SeoConfigForm.Update> seoConfigs;
    }

    @Getter
    @Setter
    public static class Meta {
        @ApiModelProperty(value = "名称", required = true, example = "keywords")
        private String name;
        @ApiModelProperty(value = "内容", required = true, example = "策知通")
        private String content;
    }

}
