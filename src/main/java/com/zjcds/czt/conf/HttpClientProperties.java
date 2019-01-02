package com.zjcds.czt.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * created date：2019-01-02
 * @author niezhegang
 */
@ConfigurationProperties("com.zjcds.httpclient")
@Getter
@Setter
public class HttpClientProperties {
    private int maxConnTotal;
    private int maxConnPerRoute;
    private int connectTimeout;
    private int socketTimeout=30000;
    private int connectionRequestTimeout;
}
