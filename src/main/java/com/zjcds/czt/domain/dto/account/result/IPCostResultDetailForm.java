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
public class IPCostResultDetailForm {

    @ApiModelProperty(value = "知识产权费用列表")
    private List<IPCost> costList;
    @ApiModelProperty(value = "费用合计，单位：元")
    private Double totalCost;

    @Getter
    @Setter
    public static class IPCost {
        @ApiModelProperty(value = "知识产权类型",example = "软件测评报告")
        private String type;
        @ApiModelProperty(value = "数量",example = "2")
        private Integer quantity;
        @ApiModelProperty(value = "费用，单位：元",example = "1000")
        private Double cost;
    }

}
