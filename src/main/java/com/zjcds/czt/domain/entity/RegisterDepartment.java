package com.zjcds.czt.domain.entity;

import javax.persistence.*;

/**
 * @author luokp on 2019/1/5.
 */
@Entity
@Table(name = "t_register_department")
public class RegisterDepartment {

    private Integer id;

    private String name;

    private String regionCode;

    @Id
    @Column(name = "id")
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
    @Column(name = "region_code")
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
