package com.zjcds.czt.dao.jpa;

import com.zjcds.common.jpa.CustomRepostory;
import com.zjcds.czt.domain.entity.jpa.SubsidyPolicy;

import java.util.List;

/**
 * @author luokp on 2018/12/22.
 */
public interface SubsidyPolicyDao extends CustomRepostory<SubsidyPolicy, Integer>, SubsidyPolicyDaoCustom {

    public List<SubsidyPolicy> findByRegionCodeLike(String regionCode);

    public List<SubsidyPolicy> findByRegionCode(String regionCode);

}
