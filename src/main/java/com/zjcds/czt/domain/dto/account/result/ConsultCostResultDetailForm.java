package com.zjcds.czt.domain.dto.account.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luokp on 2019/1/2.
 */
@Getter
@Setter
public class ConsultCostResultDetailForm {

    @ApiModelProperty(value = "企业所属高新领域费用")
    private HighFieldCost highFieldCost;
    @ApiModelProperty(value = "企业财务成长性情况费用")
    private FinancialGrowthCost financialGrowthCost;
    @ApiModelProperty(value = "企业最近一年营业收入费用")
    private RecentYearIncomingCost recentYearIncomingCost;
    @ApiModelProperty(value = "费用合计，单位：元")
    private Double totalCost;

    @Getter
    @Setter
    public static class HighFieldCost {
        @ApiModelProperty(value = "企业所属高新领域", example = "电子信息")
        private String highField;
        @ApiModelProperty(value = "费用，单位：元")
        private Double cost;
    }

    @Getter
    @Setter
    public static class FinancialGrowthCost {
        @ApiModelProperty(value = "企业财务成长性情况", example = "优秀")
        private String financialGrowth;
        @ApiModelProperty(value = "费用，单位：元")
        private Double cost;
    }

    @Getter
    @Setter
    public static class RecentYearIncomingCost {
        @ApiModelProperty(value = "企业最近一年营业收入", example = "500万元-2000万元")
        private String recentYearIncoming;
        @ApiModelProperty(value = "费用，单位：元")
        private Double cost;
    }

}
