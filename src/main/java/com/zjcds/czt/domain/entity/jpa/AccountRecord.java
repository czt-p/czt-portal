package com.zjcds.czt.domain.entity.jpa;

import javax.persistence.*;
import java.util.Date;

/**
 * @author luokp on 2019/1/10.
 */
@Entity
@Table(name = "t_account_record")
public class AccountRecord {

    private Integer id;

    private String companyName;

    private String telephone;

    private Double consultCost;

    private Double ipCost;

    private Double otherCost;

    private Double annualAuditCost;

    private Double specialAuditCost;

    private Double totalCost;

    private String details;

    private Date accountTime;

    @Id
    @Column(name = "ID")
    @TableGenerator(name = "idGenerator", table = "t_id_generator", pkColumnName = "id_key", pkColumnValue = "accountRecord", valueColumnName = "id_value")
    @GeneratedValue(generator = "idGenerator", strategy = GenerationType.TABLE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "consult_cost")
    public Double getConsultCost() {
        return consultCost;
    }

    public void setConsultCost(Double consultCost) {
        this.consultCost = consultCost;
    }

    @Basic
    @Column(name = "ip_cost")
    public Double getIpCost() {
        return ipCost;
    }

    public void setIpCost(Double ipCost) {
        this.ipCost = ipCost;
    }

    @Basic
    @Column(name = "other_cost")
    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    @Basic
    @Column(name = "annual_audit_cost")
    public Double getAnnualAuditCost() {
        return annualAuditCost;
    }

    public void setAnnualAuditCost(Double annualAuditCost) {
        this.annualAuditCost = annualAuditCost;
    }

    @Basic
    @Column(name = "special_audit_cost")
    public Double getSpecialAuditCost() {
        return specialAuditCost;
    }

    public void setSpecialAuditCost(Double specialAuditCost) {
        this.specialAuditCost = specialAuditCost;
    }

    @Basic
    @Column(name = "total_cost")
    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "account_time")
    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }


}
