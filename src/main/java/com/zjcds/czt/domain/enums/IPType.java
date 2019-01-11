package com.zjcds.czt.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author luokp on 2019/1/2.
 */
@JsonDeserialize(using = IPType.IPTypeDeserializer.class)
public enum IPType implements EnumValue<String, String> {
    FMZL("FMZL", "发明专利"),
    SYXXZL("SYXXZL", "实用新型专利"),
    WGZL("WGZL", "外观专利"),
    RJZZQ("RJZZQ", "软件著作权");

    private String key;

    private String value;

    IPType(String key, String value) {
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

    public static class IPTypeDeserializer extends EnumValueDeserializer {
        public IPTypeDeserializer() {
            super(IPType.class);
        }
    }

}
