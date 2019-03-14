package com.zjcds.czt.domain.entity.jpa;

import javax.persistence.*;

/**
 * @author luokp on 2019/3/13.
 */
@Entity
@Table(name = "t_seo_config")
public class SeoConfig {

    private String pageCode;

    private String title;

    private String meta;

    private FrontendPage page;

    @Id
    @Column(name = "page_code", insertable = false, updatable = false)
    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }


    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "meta")
    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    @OneToOne
    @JoinColumn(name = "page_code", referencedColumnName = "code")
    public FrontendPage getPage() {
        return page;
    }

    public void setPage(FrontendPage page) {
        this.page = page;
    }
}
