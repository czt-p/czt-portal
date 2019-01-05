package com.zjcds.czt.service.impl;

import com.zjcds.czt.dao.jpa.RegisterDepartmentDao;
import com.zjcds.czt.dao.jpa.SubsidyPolicyDao;
import com.zjcds.czt.domain.entity.RegisterDepartment;
import com.zjcds.czt.domain.entity.SubsidyPolicy;
import com.zjcds.czt.service.SubsidyPolicyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author luokp on 2019/1/5.
 */
@Service
@Transactional(readOnly = true)
public class SubsidyPolicyServiceImpl implements SubsidyPolicyService {

    @Autowired
    private SubsidyPolicyDao subsidyPolicyDao;

    @Autowired
    private RegisterDepartmentDao registerDepartmentDao;

    @Override
    public List<SubsidyPolicy> queryByRegion(String regionCode) {
        if (regionCode.endsWith("0000")) {
            regionCode = regionCode.replaceFirst("0000", "");
        } else if (regionCode.endsWith("00")) {
            regionCode = regionCode.replaceFirst("00", "");
        }
        return subsidyPolicyDao.findByRegionCodeLike("%" + regionCode + "%");
    }

    @Override
    public List<SubsidyPolicy> queryByRegisterDepartment(String name) {
        List<RegisterDepartment> rds = registerDepartmentDao.findByName(name);
        if (CollectionUtils.isNotEmpty(rds)) {
            String regionCode = rds.get(0).getRegionCode();
            if (StringUtils.isNotBlank(regionCode)) {
                return subsidyPolicyDao.findByRegionCode(regionCode);
            }
        }
        return Collections.EMPTY_LIST;
    }
}
