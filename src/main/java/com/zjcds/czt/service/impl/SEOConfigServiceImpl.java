package com.zjcds.czt.service.impl;

import com.zjcds.czt.domain.dto.SEOConfigForm;
import com.zjcds.czt.service.SEOConfigService;
import com.zjcds.czt.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author luokp on 2019/3/12.
 */
@Service
public class SEOConfigServiceImpl implements SEOConfigService {

    @Value("${czt.seoconfig.path}")
    private String filePath;

    @Override
    public SEOConfigForm getSEOConfig() {
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner in = new Scanner(new FileInputStream(filePath), "UTF-8")) {
                String json = in.nextLine();
                return JsonUtils.toObject(json, SEOConfigForm.class);
            } catch (IOException e) {
            }
        }
        return null;
    }

    @Override
    public SEOConfigForm updateSEOConfig(SEOConfigForm form) {
        try {
            File file = new File(filePath);
            File dir = new File(file.getParent());
            if (dir.exists()) {
                dir.mkdirs();
            }
            FileWriter out = new FileWriter(file, false);
            out.write(JsonUtils.toJson(form));
            out.close();
        } catch (IOException e) {
        }
        return form;
    }
}
