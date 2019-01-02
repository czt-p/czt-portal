package com.zjcds.czt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * created date：2018-12-29
 * @author niezhegang
 */
public class RestTemplateTest extends SpringBootTestSupport {
    @Autowired
    private RestTemplate restTemplate;
//    @Test
//    public void name() {
//        System.out.println(restTemplate.getForObject("https://www.baidu.com/",String.class));
//    }
}
