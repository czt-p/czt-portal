package com.zjcds.czt.domain.dto.account;

import com.zjcds.czt.domain.enums.IPType;
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
public class IPCostForm {

    @ApiModelProperty(value = "知识产权列表")
    private List<IPCalc> ipList;

    @Getter
    @Setter
    public static class IPCalc {
        @NotNull
        @ApiModelProperty(value = "知识产权类型")
        private IPType type;
        @NotNull
        @ApiModelProperty(value = "数量")
        private Integer quantity;

    }

}
