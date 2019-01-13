package com.zjcds.czt.domain.enums;

/**
 * @author luokp on 2019/1/2.
 */
public enum OtherCostType implements EnumValue<String, String> {
    RJPCBG("RJPCBG", "软件评测报告（不包含代理费）"),
    CXBG("CXBG", "查新报告（不包含代理费）"),
    CPJCBG("CPJCBG", "产品检测报告（不包含代理费）"),
    CWYFFDDJZ("CWYFFDDJZ", "财务研发费单独建账"),
    SJXCP("SJXCP", "省级新产品");

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
