package com.zjcds.czt.conf;

import org.springframework.context.annotation.Configuration;

/**
 * Favicon配置
 * created date：2016-12-08
 * @author niezhegang
 */
@Configuration
public class FaviconConfiguration {

//    @Bean
//    public SimpleUrlHandlerMapping faviconHandlerMapping() {
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        mapping.setUrlMap(Collections.singletonMap("**/favicon.ico",
//                faviconRequestHandler()));
//        return mapping;
//    }
//
//    @Bean
//    public ResourceHttpRequestHandler faviconRequestHandler() {
//        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
//        requestHandler
//                .setLocations(Arrays.<Resource> asList(new ClassPathResource("/META-INF/resources/")));
//        return requestHandler;
//    }
}
