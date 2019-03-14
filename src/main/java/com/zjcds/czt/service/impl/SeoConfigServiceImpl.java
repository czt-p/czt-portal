package com.zjcds.czt.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zjcds.czt.dao.jpa.FrontendPageDao;
import com.zjcds.czt.dao.jpa.SeoConfigDao;
import com.zjcds.czt.domain.dto.SeoConfigForm;
import com.zjcds.czt.domain.entity.jpa.FrontendPage;
import com.zjcds.czt.domain.entity.jpa.SeoConfig;
import com.zjcds.czt.service.SeoConfigService;
import com.zjcds.czt.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luokp on 2019/3/12.
 */
@Service
public class SeoConfigServiceImpl implements SeoConfigService {

    @Autowired
    private SeoConfigDao seoConfigDao;

    @Autowired
    private FrontendPageDao frontendPageDao;

    @Override
    public List<SeoConfigForm.Owner> querySeoConfigs() {
        List<SeoConfigForm.Owner> result = new ArrayList<>();
        List<FrontendPage> pages = frontendPageDao.findAllForFetchSeoConfig();
        try {
            for (FrontendPage page : pages) {
                SeoConfigForm.Owner seoConfig = new SeoConfigForm.Owner();
                seoConfig.setPageCode(page.getCode());
                seoConfig.setPageName(page.getName());
                seoConfig.setPageDescription(page.getDescription());
                if (page.getSeoConfig() != null) {
                    seoConfig.setTitle(page.getSeoConfig().getTitle());
                    if (StringUtils.isNotBlank(page.getSeoConfig().getMeta())) {
                        seoConfig.setMeta(JsonUtils.toObject(page.getSeoConfig().getMeta(), new TypeReference<List<SeoConfigForm.Meta>>() {
                        }));
                    }
                }
                result.add(seoConfig);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void updateSeoConfigs(SeoConfigForm.UpdateList form) {
        List<SeoConfig> entityList = new ArrayList<>();
        for (SeoConfigForm.Update seoConfig : form.getSeoConfigs()) {
            SeoConfig entity = new SeoConfig();
            FrontendPage page = frontendPageDao.findOne(seoConfig.getPageCode());
            Assert.notNull(page, "页面[code=" + seoConfig.getPageCode() + "]不存在");
            entity.setPageCode(page.getCode());
            entity.setPage(page);
            entity.setTitle(seoConfig.getTitle());
            entity.setMeta(JsonUtils.toJson(seoConfig.getMeta()));
            entityList.add(entity);
        }
        seoConfigDao.save(entityList);
    }

    @Override
    public SeoConfigForm.Owner querySeoConfigByPageCode(String pageCode) {
        FrontendPage page = frontendPageDao.findOneForFetchSeoConfig(pageCode);
        Assert.notNull(page, "页面[code=" + pageCode + "]不存在");
        try {
            SeoConfigForm.Owner seoConfig = new SeoConfigForm.Owner();
            seoConfig.setPageCode(page.getCode());
            seoConfig.setPageName(page.getName());
            seoConfig.setPageDescription(page.getDescription());
            if (page.getSeoConfig() != null) {
                seoConfig.setTitle(page.getSeoConfig().getTitle());
                if (StringUtils.isNotBlank(page.getSeoConfig().getMeta())) {
                    seoConfig.setMeta(JsonUtils.toObject(page.getSeoConfig().getMeta(), new TypeReference<List<SeoConfigForm.Meta>>() {
                    }));
                }
            }
            return seoConfig;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
