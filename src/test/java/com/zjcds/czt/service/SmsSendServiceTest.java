package com.zjcds.czt.service;

import com.zjcds.czt.SpringBootTestSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * created date：2018-12-30
 * @author niezhegang
 */
public class SmsSendServiceTest extends SpringBootTestSupport{
    @Autowired
    private SmsSendService smsSendService;

//    @Test
//    public void testGxpc() {
//        Map<String,Object> variables = new HashMap<>();
//        variables.put("pczf","100");
//        variables.put("zzcq","优秀");
//        variables.put("hjcg","优秀");
//        variables.put("glsp","优秀");
//        variables.put("cwzz","优秀");
//        System.out.println(smsSendService.singleSend("13376827640","gxpc",variables));
//    }
//
//    @Test
//    public void testGxfyhs() {
//        Map<String,Object> variables = new HashMap<>();
//        variables.put("zfy","10,000");
//        variables.put("zxfy","20,000");
//        variables.put("zscqfy","30,000");
//        variables.put("qtfy","40,000");
//        variables.put("ndsjbgfy","50,000");
//        variables.put("zxsjbgfy","60,000");
//        System.out.println(smsSendService.singleSend("13376827640","gxfyhs",variables));
//    }

}
