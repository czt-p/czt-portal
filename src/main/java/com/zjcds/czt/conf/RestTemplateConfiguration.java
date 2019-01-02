package com.zjcds.czt.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * created date：2018-12-29
 * @author niezhegang
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate() {

        return new RestTemplate(new CustomHttpComponentsClientHttpRequestFactory());

    }

    public static  class CustomHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    }
}
