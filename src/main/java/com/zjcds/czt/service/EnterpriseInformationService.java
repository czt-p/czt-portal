package com.zjcds.czt.service;

import com.alibaba.fastjson.JSONPath;
import com.zjcds.czt.conf.EiProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * 企业信息查询服务
 * created date：2018-12-30
 * @author niezhegang
 */
@Service
@EnableConfigurationProperties(EiProperties.class)
@Slf4j
public class EnterpriseInformationService implements InitializingBean {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EiProperties eiProperties;

    private volatile String token;

    private String tokenQueryUri;

    private AtomicBoolean tokenlock = new AtomicBoolean(false);

    @Override
    public void afterPropertiesSet() throws Exception {
        tokenQueryUri = UriComponentsBuilder.fromHttpUrl(eiProperties.getGetTokenUrl())
                .queryParam("appkey",eiProperties.getAppkey())
                .queryParam("type",eiProperties.getType())
                .queryParam("seckey",eiProperties.getSeckey()).toUriString();
    }

    /**
     * 根据企业名称（全名）查询注册机构
     * @param enterpriseName
     * @return
     */
    public String queryRegisterOrgName(String enterpriseName) {
        String currentToken = token;
        if(StringUtils.isBlank(currentToken)){
            currentToken = queryCurrentToken();
        }
        String response = queryRegisterOrgNameInner(enterpriseName,currentToken);
        Integer status = (Integer)JSONPath.extract(response,"$.status");
        //token过期
        if(status == 101) {
            currentToken = queryCurrentToken();
            response = queryRegisterOrgNameInner(enterpriseName,currentToken);
            status = (Integer)JSONPath.extract(response,"$.status");
        }
        //查询成功
        if(status == 200) {
            return (String) JSONPath.extract(response,"$.result.regOrgName");
        }
        else
            return null;
    }

    private String queryRegisterOrgNameInner(String enterpriseName,String currentToken) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(eiProperties.getOrgCompanyListByCompanyNameUrl())
                .queryParam("token",currentToken)
                .queryParam("type",eiProperties.getType())
                .queryParam("companyName",enterpriseName);
        try {
            return restTemplate.getForObject(builder.toUriString(),String.class);
        }
        catch (RestClientResponseException restClientResponseException) {
            log.error(restClientResponseException.getResponseBodyAsString(),restClientResponseException);
            throw restClientResponseException;
        }
    }

    /**
     * 查询当前token
     * @return
     */
    public String queryCurrentToken(){
        while (true) {
            if(tokenlock.compareAndSet(false,true)) {
                try {
                    String response = restTemplate.getForObject(tokenQueryUri,String.class);
                    Integer status = (Integer) JSONPath.extract(response,"$.status");
                    if(status == 200) {
                        token = (String) JSONPath.extract(response, "$.result.token");
                        return token;
                    }
                    else {
                        throw new IllegalStateException("获取token失败：" + (String) JSONPath.extract(response, "$.result.message"));
                    }
                }
                catch (RestClientResponseException restClientResponseException) {
                    log.error(restClientResponseException.getResponseBodyAsString(),restClientResponseException);
                    throw restClientResponseException;
                }
                finally {
                    tokenlock.set(false);
                }
            }
        }
    }

}
