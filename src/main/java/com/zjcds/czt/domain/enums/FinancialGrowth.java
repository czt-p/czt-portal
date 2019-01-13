package com.zjcds.czt.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author luokp on 2019/1/2.
 */
@JsonDeserialize(using = FinancialGrowth.FinancialGrowthDeserializer.class)
public enum FinancialGrowth implements EnumValue<String, String> {
    A("A", "优秀"),
    B("B", "良好"),
    C("C", "一般"),
    D("D", "差");

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
