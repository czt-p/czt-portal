package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * created date：2018-12-24
 * @author niezhegang
 */
public class HighTechQuestionForm extends BaseBean{

    @Getter
    @Setter
    @ApiModel(value = "HighTechQuestion",description = "高新问题对象")
    public static class Owner {
        @ApiModelProperty(value = "ID",required = true,readOnly = true)
        private String id;
        @ApiModelProperty(value = "问题标题",required = true,readOnly = true)
        private String question;
        @ApiModelProperty(value = "问题回答",required = true,readOnly = true)
        private String answer;
        @ApiModelProperty(value = "问题添加时间",required = true,readOnly = true)
        private Date addtime;
        @ApiModelProperty(value = "问题修改时间",required = true,readOnly = true)
        private Date updatedTime;
    }


}
