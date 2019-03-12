package com.zjcds.czt.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luokp on 2019/3/11.
 */
@Getter
@Setter
public class SEOConfigForm {

    private String title;

    private List<Meta> meta;

    @Getter
    @Setter
    public static class Meta {

        private String name;

        private String content;
    }

}
