package com.zjcds.czt.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author luokp on 2019/1/2.
 */
@JsonDeserialize(using = IPType.IPTypeDeserializer.class)
public enum IPType implements EnumValue<String, String> {
    FMZL("FMZL", "发明专利（无费减、含代理费）"),
    SYXXZL("SYXXZL", "实用新型专利（无费减、含代理费）"),
    WGZL("WGZL", "外观专利（无费减、含代理费）"),
    RJZZQ("RJZZQ", "软件著作权(31工作日加急、含代理费)"),
    JCDLBTSJ("JCDLBTSJ", "集成电路布图设计"),
    FMZL85("FMZL85", "发明专利（85%费减、含代理费）"),
    SYXXZL85("SYXXZL85", "实用新型专利（85%费减、含代理费）"),
    WGZL85("WGZL85", "外观专利（85%费减、含代理费）"),
    FMZL70("FMZL70", "发明专利（70%费减、含代理费）"),
    SYXXZL70("SYXXZL70", "实用新型专利（70%费减、含代理费）"),
    WGZL70("WGZL70", "外观专利（70%费减、含代理费）");

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
