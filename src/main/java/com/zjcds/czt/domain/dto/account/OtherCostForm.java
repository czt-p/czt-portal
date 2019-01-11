package com.zjcds.czt.domain.dto.account;

import com.zjcds.czt.domain.enums.OtherCostType;
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
public class OtherCostForm {

    @ApiModelProperty(value = "其他列表")
    private List<OtherCalc> otherList;

    @Getter
    @Setter
    public static class OtherCalc {
        @NotNull
        @ApiModelProperty(value = "费用类型")
        private OtherCostType type;
        @NotNull
        @ApiModelProperty(value = "数量")
        private Integer quantity;
    }

}
