package com.zjcds.czt.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * created date：2018-12-30
 * @author niezhegang
 */
@ConfigurationProperties(prefix = "com.zjcds.ei")
@Getter
@Setter
public class EiProperties {
    /**appkey*/
    private String appkey;
    /**安全秘钥*/
    private String seckey;
    /**返回格式类型*/
    private String type;
    /**根据企业全名查询机构信息api对应url*/
    private String orgCompanyListByCompanyNameUrl;
    /**获取token的url*/
    private String getTokenUrl;
}
