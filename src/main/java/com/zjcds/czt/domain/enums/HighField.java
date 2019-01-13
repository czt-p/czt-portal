package com.zjcds.czt.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author luokp on 2019/1/2.
 */
@JsonDeserialize(using = HighField.HighFieldDeserializer.class)
public enum HighField implements EnumValue<String, String> {
    DZXX("DZXX", "电子信息"),
    SWYXYY("SWYXYY", "生物与新医药"),
    HKHT("HKHT", "航空航天"),
    XCL("XCL", "新材料"),
    GXJSFW("GXJSFW", "高技术服务"),
    XNYYJN("XNYYJN", "新能源与节能"),
    ZYYHJ("ZYYHJ", "资源与环境"),
    XJZZYZDH("XJZZYZDH", "先进制造与自动化"),
    QT("QT", "其他");

    private String key;

    private String value;

    HighField(String key, String value) {
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

    public static class HighFieldDeserializer extends EnumValueDeserializer {
        public HighFieldDeserializer() {
            super(HighField.class);
        }
    }
}
