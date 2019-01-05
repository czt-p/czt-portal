package com.zjcds.czt.domain.dto;

import com.zjcds.common.base.domain.BaseBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luokp on 2019/1/5.
 */
public class OptionForm {

    @Getter
    @Setter
    public static class Owner extends BaseBean {
        private Integer id;
        private String content;
        private Double score;
    }

    @Getter
    @Setter
    public static class OwnerWithoutScore extends BaseBean {
        private Integer id;
        private String content;
    }

}
