package com.zjcds.czt.service;

import com.zjcds.czt.SpringBootTestSupport;
import com.zjcds.czt.domain.entity.jpa.SubsidyPolicy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public class SubsidyPolicyServiceTest extends SpringBootTestSupport {

    @Autowired
    private SubsidyPolicyService subsidyPolicyService;

    @Test
    public void test() {
        List<SubsidyPolicy> result1 = subsidyPolicyService.queryByRegion("330000");
        List<SubsidyPolicy> result2 = subsidyPolicyService.queryByRegisterDepartment("杭州市江干区市场监督管理局");
        System.out.println();
    }

}
