package com.zjcds.czt.domain.dto.account;

import com.zjcds.czt.domain.enums.FinancialGrowth;
import com.zjcds.czt.domain.enums.HighField;
import com.zjcds.czt.domain.enums.RecentYearIncoming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author luokp on 2019/1/2.
 */
@Getter
@Setter
public class ConsultCostForm {

    @NotNull
    @ApiModelProperty(value = "企业所属高新领域")
    private HighField highField;
    @NotNull
    @ApiModelProperty(value = "企业财务成长性情况")
    private FinancialGrowth financialGrowth;
    @NotNull
    @ApiModelProperty(value = "企业最近一年营业收入")
    private RecentYearIncoming recentYearIncoming;

}
