package com.zjcds.czt.service;

import com.zjcds.czt.domain.dto.RegionForm;

import java.util.List;

/**
 * @author luokp on 2018/12/22.
 */
public interface RegionService {

    /**
     * 查询所有行政地区
     *
     * @return
     */
    public List<RegionForm.Owner> queryAllRegions();

}
