package com.zjcds.czt.domain.entity;

import javax.persistence.*;

/**
 * @author luokp on 2018/12/22.
 */
@Entity
@Table(name = "t_region")
public class Region {

    /**
     * 行政区划代码
     */
    private String code;

    /**
     * 行政区划名称
     */
    private String name;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 父级代码
     */
    private String parent;

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
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "parent")
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
