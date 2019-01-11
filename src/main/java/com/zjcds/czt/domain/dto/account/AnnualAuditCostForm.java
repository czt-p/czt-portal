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
public class AnnualAuditCostForm {

    @NotNull
    @ApiModelProperty(value = "预计申请年度")
    private Integer applyYear;

    @ApiModelProperty(value = "年度审核列表")
    private List<AnnualAuditCalc> annualAuditList;

    @Getter
    @Setter
    public static class AnnualAuditCalc {
        @NotNull
        @ApiModelProperty(value = "年份")
        private Integer year;
        @NotNull
        @ApiModelProperty(value = "金额")
        private Double amount;
    }

}
