package com.zjcds.czt.conf;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * created date：2018-12-29
 * @author niezhegang
 */
@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
public class RestTemplateConfiguration {
    @Autowired
    private HttpClientProperties httpClientProperties;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(toHttpClientBuilder().build()));

    }

    private HttpClientBuilder toHttpClientBuilder() {
        return  HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                                        .setConnectTimeout(httpClientProperties.getConnectTimeout())
                                        .setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout())
                                        .setSocketTimeout(httpClientProperties.getSocketTimeout())
                                        .build())
                .setMaxConnPerRoute(httpClientProperties.getMaxConnPerRoute())
                .setMaxConnTotal(httpClientProperties.getMaxConnTotal())
                ;
    }
}
