package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author luokp on 2018/12/22.
 */
public class SubsidyPolicyForm {

    @Getter
    @Setter
    @ApiModel(value = "subsidyPolicy", description = "资助政策")
    public static class Owner extends BaseBean {
        @ApiModelProperty(value = "资助政策ID", required = true, readOnly = true)
        private Integer id;
        @ApiModelProperty(value = "资助政策名称", required = true, example = "杭州市高新区知识产权资助政策")
        private String name;
        @ApiModelProperty(value = "政策所属地区行政区划代码", required = true, example = "330104")
        private String regionCode;
        @ApiModelProperty(value = "内容", required = true, example = "内容...")
        private String content;
        @ApiModelProperty(value = "创建时间", dataType = "long", example = "1545449085000")
        private Date createTime;
        @ApiModelProperty(value = "修改时间", dataType = "long", example = "1545449085000")
        private Date modifyTime;
        @ApiModelProperty(value = "政策所属地区", required = true)
        private RegionForm.Owner region;
    }

}
