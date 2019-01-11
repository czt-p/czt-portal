package com.zjcds.czt.domain.enums;

/**
 * @author luokp on 2019/1/2.
 */
public enum OtherCostType implements EnumValue<String, String> {
    RJPCBG("RJPCBG", "软件评测报告"),
    CXBG("CXBG", "查新报告"),
    CPJCBG("CPJCBG", "产品检测报告"),
    CWGFFD("CWGFFD", "财务规范辅导");

    private String key;

    private String value;

    OtherCostType(String key, String value) {
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

    public static class OtherCostTypeDeserializer extends EnumValueDeserializer {
        public OtherCostTypeDeserializer() {
            super(OtherCostType.class);
        }
    }

}
