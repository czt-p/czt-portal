package com.zjcds.czt.domain.entity;

import com.zjcds.common.jpa.domain.CreateModifyTime;

import javax.persistence.*;

/**
 * @author luokp on 2018/12/22.
 */
@Entity
@Table(name = "t_subsidy_policy")
public class SubsidyPolicy extends CreateModifyTime {

    /**
     * id
     **/
    private Integer id;

    /**
     * 政策名称
     **/
    private String name;

    /**
     * 所属地区代码
     **/
    private String regionCode;

    /**
     * 内容
     **/
    private String content;

    private Region region;

    @Id
    @Column(name = "ID")
    @TableGenerator(name = "idGenerator", table = "t_id_generator", pkColumnName = "id_key", pkColumnValue = "subsidyPolicy", valueColumnName = "id_value")
    @GeneratedValue(generator = "idGenerator", strategy = GenerationType.TABLE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "region_code", insertable = false, updatable = false)
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToOne
    @JoinColumn(name = "region_code", referencedColumnName = "code")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
