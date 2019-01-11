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
public class AnnualAuditCostResultDetailForm {

    @ApiModelProperty(value = "预计申请年度")
    private Integer applyYear;
    @ApiModelProperty(value = "年度审计报告费用列表")
    private List<AnnualAuditCost> costList;
    @ApiModelProperty(value = "费用合计，单位：元")
    private Double totalCost;

    @Getter
    @Setter
    public static class AnnualAuditCost {
        @ApiModelProperty(value = "年份")
        private Integer year;
        @ApiModelProperty(value = "资产总额，单位：万元")
        private Double amount;
        @ApiModelProperty(value = "费用，单位：元")
        private Double cost;
    }

}
