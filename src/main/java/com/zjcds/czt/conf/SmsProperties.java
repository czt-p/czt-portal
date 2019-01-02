package com.zjcds.czt.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * created date：2018-12-29
 * @author niezhegang
 */
@ConfigurationProperties(prefix = "com.zjcds.sms")
@Getter
@Setter
public class SmsProperties {
    /**apikey*/
    private String apikey;
    /**单条发送api对应的url*/
    private String singleSendUrl;
    /**客服电话*/
    private String customerServicePhone;

}
