package com.zjcds.czt.domain.dto.account.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luokp on 2019/1/2.
 */
@Getter
@Setter
public class CostResultDetailForm {

    @ApiModelProperty(value = "咨询费用")
    private ConsultCostResultDetailForm consultCost;
    @ApiModelProperty(value = "知识产权费用费用")
    private IPCostResultDetailForm ipCost;
    @ApiModelProperty(value = "其他费用")
    private OtherCostResultDetailForm otherCost;
    @ApiModelProperty(value = "年度审计报告费用")
    private AnnualAuditCostResultDetailForm annualAuditCost;
    @ApiModelProperty(value = "专项审计报告费用")
    private SpecialAuditCostResultDetailForm specialAuditCost;

    public CostResultDetailForm() {
    }

    public CostResultDetailForm(ConsultCostResultDetailForm consultCost, IPCostResultDetailForm ipCost, OtherCostResultDetailForm otherCost, AnnualAuditCostResultDetailForm annualAuditCost, SpecialAuditCostResultDetailForm specialAuditCost) {
        this.consultCost = consultCost;
        this.ipCost = ipCost;
        this.otherCost = otherCost;
        this.annualAuditCost = annualAuditCost;
        this.specialAuditCost = specialAuditCost;
    }
}
