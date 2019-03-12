package com.zjcds.czt.service;

import com.zjcds.czt.domain.dto.SEOConfigForm;

/**
 * @author luokp on 2019/3/12.
 */
public interface SEOConfigService {

    public SEOConfigForm getSEOConfig();

    public SEOConfigForm updateSEOConfig(SEOConfigForm form);

}
