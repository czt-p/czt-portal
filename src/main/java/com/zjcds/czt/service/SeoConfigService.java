package com.zjcds.czt.service;

import com.zjcds.czt.domain.dto.SeoConfigForm;

import java.util.List;

/**
 * @author luokp on 2019/3/12.
 */
public interface SeoConfigService {

    public List<SeoConfigForm.Owner> querySeoConfigs();

    public void updateSeoConfigs(SeoConfigForm.UpdateList form);

    public SeoConfigForm.Owner querySeoConfigByPageCode(String pageCode);

}
