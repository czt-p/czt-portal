package com.zjcds.czt.service;

import com.zjcds.czt.domain.entity.SubsidyPolicy;

import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
public interface SubsidyPolicyService {

    /**
     * 根据行政区划代码查询资助政策
     *
     * @param regionCode 行政区划代码
     * @return
     */
    public List<SubsidyPolicy> queryByRegion(String regionCode);

    /**
     * 根据登记机关查询资助政策
     *
     * @param name 资助机关
     * @return
     */
    public List<SubsidyPolicy> queryByRegisterDepartment(String name);

    /**
     * 根据企业名称查询其能享受的资助政策
     * @param companyName
     * @return
     */
    public List<SubsidyPolicy> querySubsidyPolicyByCompanyName(String companyName);

}
