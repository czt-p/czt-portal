package com.zjcds.czt.domain.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author luokp on 2019/1/2.
 */
@Getter
@Setter
public class CostAccountForm {

    @NotBlank
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @NotBlank
    @ApiModelProperty(value = "手机号码")
    private String telephone;
    @NotNull
    @ApiModelProperty(value = "咨询费用")
    private ConsultCostForm consultCost;
    @ApiModelProperty(value = "知识产权费用")
    private IPCostForm ipCost;
    @ApiModelProperty(value = "其他费用")
    private OtherCostForm otherCost;
    @ApiModelProperty(value = "年度审计报告费用")
    private AnnualAuditCostForm annualAuditCost;
    @NotNull
    @ApiModelProperty(value = "专项审计报告费用")
    private SpecialAuditCostForm specialAuditCost;

}
