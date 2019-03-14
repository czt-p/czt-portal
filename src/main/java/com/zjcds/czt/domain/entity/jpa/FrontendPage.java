package com.zjcds.czt.domain.entity.jpa;

import javax.persistence.*;

/**
 * @author luokp on 2019/3/13.
 */
@Entity
@Table(name = "t_frontend_page")
public class FrontendPage {

    private String code;

    private String name;

    private String description;

    private SeoConfig seoConfig;

    @Id
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(mappedBy = "page")
    public SeoConfig getSeoConfig() {
        return seoConfig;
    }

    public void setSeoConfig(SeoConfig seoConfig) {
        this.seoConfig = seoConfig;
    }
}
