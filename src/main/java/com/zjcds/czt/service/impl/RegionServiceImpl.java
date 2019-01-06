package com.zjcds.czt.service.impl;

import com.zjcds.common.dozer.BeanPropertyCopyUtils;
import com.zjcds.czt.dao.jpa.RegionDao;
import com.zjcds.czt.domain.dto.RegionForm;
import com.zjcds.czt.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luokp on 2018/12/22.
 */
@Service
@Transactional(readOnly = true)
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public List<RegionForm.Owner> queryAllRegions() {
        return BeanPropertyCopyUtils.copy(regionDao.findAll(), RegionForm.Owner.class);
    }

}
