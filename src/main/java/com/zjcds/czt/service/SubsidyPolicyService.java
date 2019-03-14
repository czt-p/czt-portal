package com.zjcds.czt.service;

import com.zjcds.common.base.domain.page.Paging;
import com.zjcds.common.jpa.PageResult;
import com.zjcds.czt.domain.entity.jpa.SubsidyPolicy;

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
     *
     * @param companyName
     * @return
     */
    public List<SubsidyPolicy> querySubsidyPolicyByCompanyName(String companyName);

    /**
     * 查询资助政策
     *
     * @param paging      分页信息
     * @param queryString 查询条件
     * @param orderBy     排序
     * @return
     */
    public PageResult<SubsidyPolicy> querySubsidyPolicies(Paging paging, List<String> queryString, List<String> orderBy);

    /**
     * 查询最新更新的10条资助政策
     *
     * @return
     */
    public List<SubsidyPolicy> queryRecent10UpdateSubsidyPolicy();

}
