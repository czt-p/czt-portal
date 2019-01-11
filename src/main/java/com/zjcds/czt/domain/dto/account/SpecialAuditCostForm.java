package com.zjcds.czt.domain.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author luokp on 2019/1/2.
 */
@Getter
@Setter
public class SpecialAuditCostForm {

    @NotNull
    @ApiModelProperty(value = "预计申请年度")
    private Integer applyYear;

    @NotNull
    @ApiModelProperty(value = "专项审核")
    private List<SpecialAuditCalc> specialAuditList;

    @Getter
    @Setter
    public static class SpecialAuditCalc {
        @NotNull
        @ApiModelProperty(value = "年份")
        private Integer year;
        @NotNull
        @ApiModelProperty(value = "营业收入")
        private Double incoming;
        @NotNull
        @ApiModelProperty(value = "管理费用")
        private Double managerCost;
        @NotNull
        @ApiModelProperty(value = "研发费用")
        private Double rdCost;
    }
}
