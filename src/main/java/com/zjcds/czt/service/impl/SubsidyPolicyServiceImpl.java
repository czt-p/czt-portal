package com.zjcds.czt.service.impl;

import com.zjcds.czt.dao.jpa.RegisterDepartmentDao;
import com.zjcds.czt.dao.jpa.SubsidyPolicyDao;
import com.zjcds.czt.domain.entity.RegisterDepartment;
import com.zjcds.czt.domain.entity.SubsidyPolicy;
import com.zjcds.czt.service.EnterpriseInformationService;
import com.zjcds.czt.service.SubsidyPolicyService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SubsidyPolicyServiceImpl implements SubsidyPolicyService {

    @Autowired
    private SubsidyPolicyDao subsidyPolicyDao;

    @Autowired
    private RegisterDepartmentDao registerDepartmentDao;
    @Autowired
    private EnterpriseInformationService enterpriseInformationService;

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
        else {
            log.warn("未查询到登记机构["+name+"]对应的区域代码！");
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<SubsidyPolicy> querySubsidyPolicyByCompanyName(String companyName) {
        //先查询登记机构名称
        String orgNamme = enterpriseInformationService.queryRegisterOrgName(companyName);
        if(StringUtils.isBlank(orgNamme)) {
            log.warn("未查询到企业["+companyName+"]对应的机构名称！");
            return Collections.emptyList();
        }
        return queryByRegisterDepartment(orgNamme);
    }
}
