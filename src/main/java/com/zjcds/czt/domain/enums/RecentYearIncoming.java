package com.zjcds.czt.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author luokp on 2019/1/2.
 */
@JsonDeserialize(using = RecentYearIncoming.RecentYearIncomingDeserializer.class)
public enum RecentYearIncoming implements EnumValue<String, String> {
    A("A", "500万元以内"),
    B("B", "500-2000万元以内"),
    C("C", "2001万元-5000万元"),
    D("D", "5001万元-2亿"),
    E("E", "2亿-4亿"),
    F("F", "4亿以上");

    private String key;
    private String value;

    RecentYearIncoming(String key, String value) {
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

    public static class RecentYearIncomingDeserializer extends EnumValueDeserializer {
        public RecentYearIncomingDeserializer() {
            super(RecentYearIncoming.class);
        }
    }

}
