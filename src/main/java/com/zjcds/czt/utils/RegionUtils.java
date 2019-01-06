package com.zjcds.czt.utils;

import com.google.common.collect.FluentIterable;
import com.zjcds.common.dozer.BeanPropertyCopyUtils;
import com.zjcds.czt.domain.dto.RegionForm;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luokp on 2018/12/22.
 */
public abstract class RegionUtils {

    /**
     * 将行政地区转成级联形式
     *
     * @param regions
     * @return
     */
    public static List<RegionForm.Cascade> transformToCascade(List<RegionForm.Owner> regions) {
        List<RegionForm.Cascade> regionForms = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(regions)) {
            List<RegionForm.Owner> rootRegions = findRootRegions(regions);
            RegionForm.Cascade rootRegionForm = null;
            for (RegionForm.Owner rootRegion : rootRegions) {
                rootRegionForm = BeanPropertyCopyUtils.copy(rootRegion, RegionForm.Cascade.class);
                fillChildren(rootRegionForm, regions);
                regionForms.add(rootRegionForm);
            }
        }
        return regionForms;
    }

    /**
     * 查询根行政地区，根据parent为空
     *
     * @param regions
     * @return
     */
    private static List<RegionForm.Owner> findRootRegions(List<RegionForm.Owner> regions) {
        List<RegionForm.Owner> rootRegions = FluentIterable.from(regions).filter(region -> {
            return StringUtils.isBlank(region.getParent());
        }).toList();
        return rootRegions;
    }

    /**
     * 填充子行政地区
     *
     * @param parent
     * @param regions
     */
    private static void fillChildren(RegionForm.Cascade parent, List<RegionForm.Owner> regions) {
        if (CollectionUtils.isNotEmpty(regions)) {
            List<RegionForm.Owner> childRegions = FluentIterable.from(regions).filter(region -> {
                return parent.getCode().equals(region.getParent());
            }).toList();
            if (CollectionUtils.isNotEmpty(childRegions)) {
                RegionForm.Cascade regionForm = null;
                for (RegionForm.Owner childRegion : childRegions) {
                    regionForm = BeanPropertyCopyUtils.copy(childRegion, RegionForm.Cascade.class);
                    parent.addChild(regionForm);
                    fillChildren(regionForm, regions);
                }
            }
        }
    }

}
