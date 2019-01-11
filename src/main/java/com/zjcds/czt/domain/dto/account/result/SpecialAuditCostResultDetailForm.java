package com.zjcds.czt.domain.dto.account.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luokp on 2019/1/2.
 */
@Getter
@Setter
public class SpecialAuditCostResultDetailForm {

    @ApiModelProperty(value = "申请年度")
    private Integer applyYear;
    @ApiModelProperty(value = "专项审计报告费用")
    private List<SpecialAuditCost> costList;
    @ApiModelProperty(value = "费用合计，单位：元")
    private Double totalCost;

    @Getter
    @Setter
    public static class SpecialAuditCost {
        @ApiModelProperty(value = "年份")
        private Integer year;
        @ApiModelProperty(value = "营业收入，单位：万元")
        private Double incoming;
        @ApiModelProperty(value = "管理费用，单位：万元")
        private Double managerCost;
        @ApiModelProperty(value = "研发费用，单位：万元")
        private Double rdCost;
        @ApiModelProperty(value = "费用，单位：元")
        private Double cost;
    }

}
