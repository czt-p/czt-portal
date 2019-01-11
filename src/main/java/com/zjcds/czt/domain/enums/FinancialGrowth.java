package com.zjcds.czt.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author luokp on 2019/1/2.
 */
@JsonDeserialize(using = FinancialGrowth.FinancialGrowthDeserializer.class)
public enum FinancialGrowth implements EnumValue<String, String> {
    A("A", "优秀"),
    B("B", "好"),
    C("C", "良好"),
    D("D", "一般"),
    E("E", "差");

    private String key;
    private String value;

    FinancialGrowth(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static class FinancialGrowthDeserializer extends EnumValueDeserializer {
        public FinancialGrowthDeserializer() {
            super(FinancialGrowth.class);
        }
    }
}
