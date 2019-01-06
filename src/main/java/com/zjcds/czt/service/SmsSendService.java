package com.zjcds.czt.service;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.LoaderException;
import com.mitchellbosecke.pebble.loader.DelegatingLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import com.zjcds.common.syslog.exception.TemplateParseException;
import com.zjcds.czt.conf.SmsProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 短信发送服务
 * created date：2018-12-29
 * @author niezhegang
 */
@Service
@EnableConfigurationProperties(SmsProperties.class)
@Slf4j
public class SmsSendService implements InitializingBean{
    /**模板处理引擎*/
    private PebbleEngine pebbleEngine;
    /**模板文本放置文件*/
    @Value("classpath:smsTemplate.csv")
    private Resource templateFile;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Loader<?>> loaders = new ArrayList<>();
        loaders.add(new FileTemplateLoader(templateFile.getInputStream()));
        pebbleEngine = new PebbleEngine
                .Builder()
                .loader(new DelegatingLoader(loaders)).build();
    }

    /**
     * 单条发送短信api
     * @param phoneNo        手机号
     * @param templateName   模板名称
     * @param variates       替换变量名值对
     * @return
     */
    public Boolean singleSend(String phoneNo, String templateName,Map<String,Object> variates) {
        String text = evaluateTemplate(templateName,appendCommonVariate(variates));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json;charset=utf-8");
        headers.set("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("apikey",smsProperties.getApikey());
        map.add("mobile",phoneNo);
        map.add("text",text);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        try {
            SmsResponse response = restTemplate.postForObject(smsProperties.getSingleSendUrl(),request,SmsResponse.class);
            return response.isSuccessed();
        }
        catch (RestClientResponseException restClientResponseException){
            log.error(restClientResponseException.getResponseBodyAsString(),restClientResponseException);
            throw restClientResponseException;
        }
    }

    /**
     * 附加一些通用变量
     * @param srcVariates
     * @return
     */
    private Map<String,Object> appendCommonVariate(Map<String,Object> srcVariates){
        if(srcVariates == null)
            srcVariates = new HashMap<>();
        srcVariates.put("lxdh",smsProperties.getCustomerServicePhone());
        return srcVariates;
    }

    private String evaluateTemplate(String templateName,Map<String,Object> evaluateContext) {
        try {
            PebbleTemplate pebbleTemplate = pebbleEngine.getTemplate(templateName);
            StringWriter sw = new StringWriter();
            pebbleTemplate.evaluate(sw,evaluateContext);
            return sw.toString();
        } catch (Exception e) {
            throw new TemplateParseException("模板解析出错！",e);
        }
    }

    @Getter
    @Setter
    public static class SmsResponse {
        //	0代表发送成功，其他code代表出错，详细见"返回值说明"页面
        private Integer code;
        //例如""发送成功""，或者相应错误信息
        private String msg;
        //发送成功短信的计费条数(计费条数：70个字一条，超出70个字时按每67字一条计费)
        private Integer count;
        //扣费金额，单位：元，类型：双精度浮点型/double
        private double fee;
        //计费单位；例如：“RMB”
        private String unit;
        //发送手机号
        private String mobile;
        //短信id，64位整型， 对应Java和C#的long，不可用int解析
        private Long sid;

        /**
         * 判定响应结果
         * @return
         */
        public Boolean isSuccessed() {
            return code == 0;
        }

    }

    @Slf4j
    public static class FileTemplateLoader implements Loader<String> {

        private final Map<String, String> templateMap = new HashMap<>();

        private static String separator = "\\|\\|";

        public FileTemplateLoader(InputStream is) {
            if (is == null) {
                log.warn("短信模板配置文件不存在！");
            } else {
                LineIterator lineIterator = null;
                try {
                    lineIterator = IOUtils.lineIterator(is,"UTF-8");
                    String line;
                    while (lineIterator.hasNext()) {
                        line = lineIterator.nextLine();
                        String[] lineArray;
                        if(StringUtils.isNotBlank(line)){
                            lineArray = line.split(separator);
                            if(lineArray.length == 3){
                                templateMap.put(StringUtils.trim(lineArray[0]),StringUtils.trim(lineArray[2]));
                            }
                            else {
                                throw new IllegalArgumentException("检测到短信模板文本配置出错："+line);
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("读取短信模板配置文件出错！",e);
                    throw new IllegalStateException(e);
                }
                finally {
                    if(lineIterator != null)
                         lineIterator.close();
                }
            }
        }

        @Override
        public Reader getReader(String cacheKey) throws LoaderException {
            String templateText = templateMap.get(cacheKey);
            if(StringUtils.isBlank(templateText))
                throw new IllegalStateException("未找到对应["+cacheKey+"]短信模板！");
            return new StringReader(templateText);
        }

        @Override
        public void setCharset(String charset) {

        }

        @Override
        public void setPrefix(String prefix) {

        }

        @Override
        public void setSuffix(String suffix) {

        }

        @Override
        public String resolveRelativePath(String relativePath, String anchorPath) {
            return null;
        }

        @Override
        public String createCacheKey(String templateName) {
            return templateName;
        }
    }
}
