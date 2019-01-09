package com.zjcds.czt.dao.jpa;

import com.zjcds.czt.domain.entity.SubsidyPolicy;

import java.util.List;

/**
 * @author luokp on 2019/1/9.
 */
public interface SubsidyPolicyDaoCustom {

    public List<SubsidyPolicy> findTop10Modify();

}
