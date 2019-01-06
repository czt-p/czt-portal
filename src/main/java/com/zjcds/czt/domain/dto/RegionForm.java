package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luokp on 2018/12/22.
 */
public class RegionForm {

    @Getter
    @Setter
    @ApiModel(value = "region", description = "行政地区")
    public static class Owner extends BaseBean {
        @ApiModelProperty(value = "行政区划代码", required = true, example = "330104")
        private String code;
        @ApiModelProperty(value = "行政地区名称", required = true, example = "江干区")
        private String name;
        @ApiModelProperty(value = "全称", required = true, example = "浙江省杭州市江干区")
        private String fullName;
        @ApiModelProperty(value = "父级代码", example = "330100")
        private String parent;
    }

    @Getter
    @Setter
    @ApiModel(value = "regionCascade", description = "级联形式的行政地区")
    public static class Cascade extends Owner {
        @ApiModelProperty(value = "子级行政地区")
        private List<Cascade> children;

        public void addChild(Cascade child) {
            if (children == null) {
                children = new ArrayList<>();
            }
            children.add(child);
        }
    }

}
