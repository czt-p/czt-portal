package com.zjcds.czt.service.impl;

import com.zjcds.czt.dao.jpa.AccountRecordDao;
import com.zjcds.czt.domain.dto.account.*;
import com.zjcds.czt.domain.dto.account.result.*;
import com.zjcds.czt.domain.entity.jpa.AccountRecord;
import com.zjcds.czt.domain.enums.FinancialGrowth;
import com.zjcds.czt.domain.enums.HighField;
import com.zjcds.czt.domain.enums.RecentYearIncoming;
import com.zjcds.czt.service.CostAccountService;
import com.zjcds.czt.service.CostCalcService;
import com.zjcds.czt.service.SmsSendService;
import com.zjcds.czt.utils.JsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author luokp on 2019/1/2.
 */
@Service
public class CostAccountServiceImpl implements CostAccountService {

    public static String LXDH = "400-878-0703";

    @Autowired
    private AccountRecordDao accountRecordDao;

    @Autowired
    private CostCalcService costCalcService;

    @Autowired
    private SmsSendService smsSendService;

    @Override
    public void costAccount(CostAccountForm costAccountForm) {
        valid(costAccountForm);
        CostResultDetailForm costResultDetail = calcCostResult(costAccountForm);
        AccountRecord record = generateRecord(costAccountForm.getCompanyName(), costAccountForm.getTelephone(), costResultDetail);
        if (!sendResultSms(costAccountForm.getTelephone(), record.getTotalCost(), costResultDetail)) {
            throw new IllegalStateException("发送费用核算报告短信失败！");
        }
        accountRecordDao.save(record);
    }

    /**
     * 表单验证
     *
     * @param form
     */
    private void valid(CostAccountForm form) {
        if (form == null) {
            throw new IllegalArgumentException("成本核算内容不能为空");
        }
        if (form.getConsultCost() == null && form.getSpecialAuditCost() == null && form.getIpCost() == null && form.getAnnualAuditCost() == null && form.getOtherCost() == null) {
            throw new IllegalArgumentException("请至少填写一项成本核算内容");
        }
    }

    /**
     * 计算成本核算结果
     *
     * @param costAccountForm
     * @return
     */
    private CostResultDetailForm calcCostResult(CostAccountForm costAccountForm) {
        CostResultDetailForm result = new CostResultDetailForm();
        ConsultCostResultDetailForm consultCost = calcConsultCost(costAccountForm.getConsultCost());
        IPCostResultDetailForm ipCost = calcIPCost(costAccountForm.getIpCost());
        OtherCostResultDetailForm otherCost = calcOtherCost(costAccountForm.getOtherCost());
        AnnualAuditCostResultDetailForm annualAuditCost = calcAnnualAuditCost(costAccountForm.getAnnualAuditCost());
        SpecialAuditCostResultDetailForm specialAuditCost = calcSpecialAuditCost(costAccountForm.getSpecialAuditCost());
        result.setConsultCost(consultCost);
        result.setIpCost(ipCost);
        result.setOtherCost(otherCost);
        result.setAnnualAuditCost(annualAuditCost);
        result.setSpecialAuditCost(specialAuditCost);
        return result;
    }

    /**
     * 计算咨询费用
     *
     * @param consultCostForm 咨询费用计算表单
     * @return
     */
    private ConsultCostResultDetailForm calcConsultCost(ConsultCostForm consultCostForm) {
        if (consultCostForm == null) {
            return null;
        }
        ConsultCostResultDetailForm result = new ConsultCostResultDetailForm();
        ConsultCostResultDetailForm.HighFieldCost highFieldCost = calcHighFieldCost(consultCostForm.getHighField());
        ConsultCostResultDetailForm.FinancialGrowthCost financialGrowthCost = calcFinancialGrowthCost(consultCostForm.getFinancialGrowth());
        ConsultCostResultDetailForm.RecentYearIncomingCost recentYearIncomingCost = calcRecentYearIncomingCost(consultCostForm.getRecentYearIncoming());
        result.setHighFieldCost(highFieldCost);
        result.setFinancialGrowthCost(financialGrowthCost);
        result.setRecentYearIncomingCost(recentYearIncomingCost);
        result.setTotalCost(highFieldCost.getCost() + financialGrowthCost.getCost() + recentYearIncomingCost.getCost());
        return result;
    }

    /**
     * 计算企业所属的高新领域费用
     *
     * @param highField 企业所属的高新领域
     * @return
     */
    private ConsultCostResultDetailForm.HighFieldCost calcHighFieldCost(HighField highField) {
        ConsultCostResultDetailForm.HighFieldCost result = new ConsultCostResultDetailForm.HighFieldCost();
        result.setHighField(highField.getValue());
        result.setCost(costCalcService.calcHighFieldCost(highField));
        return result;
    }

    /**
     * 计算企业财务成长性情况费用
     *
     * @param financialGrowth 企业财务成长性情况
     * @return
     */
    private ConsultCostResultDetailForm.FinancialGrowthCost calcFinancialGrowthCost(FinancialGrowth financialGrowth) {
        ConsultCostResultDetailForm.FinancialGrowthCost result = new ConsultCostResultDetailForm.FinancialGrowthCost();
        result.setFinancialGrowth(financialGrowth.getValue());
        result.setCost(costCalcService.calcFinancialGrowthCost(financialGrowth));
        return result;
    }

    /**
     * 计算企业最近一年营业收入费用
     *
     * @param recentYearIncoming 企业最近一年营业收入
     * @return
     */
    private ConsultCostResultDetailForm.RecentYearIncomingCost calcRecentYearIncomingCost(RecentYearIncoming recentYearIncoming) {
        ConsultCostResultDetailForm.RecentYearIncomingCost result = new ConsultCostResultDetailForm.RecentYearIncomingCost();
        result.setRecentYearIncoming(recentYearIncoming.getValue());
        result.setCost(costCalcService.calcRecentYearIncomingCost(recentYearIncoming));
        return result;
    }

    /**
     * 计算知识专利费用
     *
     * @param form
     * @return
     */
    private IPCostResultDetailForm calcIPCost(IPCostForm form) {
        if (form == null) {
            return null;
        } else if (CollectionUtils.isEmpty(form.getIpList())) {
            return null;
        }
        IPCostResultDetailForm result = new IPCostResultDetailForm();
        List<IPCostResultDetailForm.IPCost> costList = new ArrayList<>();
        for (IPCostForm.IPCalc ip : form.getIpList()) {
            IPCostResultDetailForm.IPCost ipCost = new IPCostResultDetailForm.IPCost();
            ipCost.setType(ip.getType().getValue());
            ipCost.setQuantity(ip.getQuantity());
            ipCost.setCost(costCalcService.calcIPCost(ip.getType(), ip.getQuantity()));
            costList.add(ipCost);
        }
        result.setCostList(costList);
        result.setTotalCost(costList.stream().mapToDouble(IPCostResultDetailForm.IPCost::getCost).sum());
        return result;
    }

    /**
     * 计算其他费用
     *
     * @param form
     * @return
     */
    private OtherCostResultDetailForm calcOtherCost(OtherCostForm form) {
        if (form == null) {
            return null;
        } else if (CollectionUtils.isEmpty(form.getOtherList())) {
            return null;
        }
        OtherCostResultDetailForm result = new OtherCostResultDetailForm();
        List<OtherCostResultDetailForm.OtherCost> costList = new ArrayList<>();
        for (OtherCostForm.OtherCalc other : form.getOtherList()) {
            OtherCostResultDetailForm.OtherCost otherCost = new OtherCostResultDetailForm.OtherCost();
            otherCost.setType(other.getType().getValue());
            otherCost.setQuantity(other.getQuantity());
            otherCost.setCost(costCalcService.calcOtherCost(other.getType(), other.getQuantity()));
            costList.add(otherCost);
        }
        result.setCostList(costList);
        result.setTotalCost(costList.stream().mapToDouble(OtherCostResultDetailForm.OtherCost::getCost).sum());
        return result;
    }

    /**
     * 计算年度审计报告费用
     *
     * @param form
     * @return
     */
    private AnnualAuditCostResultDetailForm calcAnnualAuditCost(AnnualAuditCostForm form) {
        if (form == null) {
            return null;
        } else if (CollectionUtils.isEmpty(form.getAnnualAuditList())) {
            return null;
        }
        AnnualAuditCostResultDetailForm result = new AnnualAuditCostResultDetailForm();
        result.setApplyYear(form.getApplyYear());
        List<AnnualAuditCostResultDetailForm.AnnualAuditCost> costList = new ArrayList<>();
        for (AnnualAuditCostForm.AnnualAuditCalc annualAudit : form.getAnnualAuditList()) {
            AnnualAuditCostResultDetailForm.AnnualAuditCost annualAuditCost = new AnnualAuditCostResultDetailForm.AnnualAuditCost();
            annualAuditCost.setYear(annualAudit.getYear());
            annualAuditCost.setAmount(annualAudit.getAmount());
            annualAuditCost.setCost(costCalcService.calcAnnualAuditCost(annualAudit.getAmount()));
            costList.add(annualAuditCost);
        }
        result.setCostList(costList);
        result.setTotalCost(costList.stream().mapToDouble(AnnualAuditCostResultDetailForm.AnnualAuditCost::getCost).sum());
        return result;
    }

    /**
     * 计算专项审计报告费用
     *
     * @param form
     * @return
     */
    private SpecialAuditCostResultDetailForm calcSpecialAuditCost(SpecialAuditCostForm form) {
        if (form == null) {
            return null;
        } else if (CollectionUtils.isEmpty(form.getSpecialAuditList())) {
            throw new IllegalArgumentException("请至少填写当年专项审计报告费用内容");
        }
        SpecialAuditCostResultDetailForm result = new SpecialAuditCostResultDetailForm();
        result.setApplyYear(form.getApplyYear());
        List<SpecialAuditCostResultDetailForm.SpecialAuditCost> costList = new ArrayList<>();
        for (SpecialAuditCostForm.SpecialAuditCalc specialAudit : form.getSpecialAuditList()) {
            SpecialAuditCostResultDetailForm.SpecialAuditCost specialAuditCost = new SpecialAuditCostResultDetailForm.SpecialAuditCost();
            specialAuditCost.setYear(specialAudit.getYear());
            specialAuditCost.setIncoming(specialAudit.getIncoming());
            specialAuditCost.setManagerCost(specialAudit.getManagerCost());
            specialAuditCost.setRdCost(specialAudit.getRdCost());
            specialAuditCost.setCost(costCalcService.calcSpecialAuditCost(specialAudit.getIncoming(), specialAudit.getManagerCost(), specialAudit.getRdCost()));
            costList.add(specialAuditCost);
        }
        result.setCostList(costList);
        result.setTotalCost(costList.stream().mapToDouble(SpecialAuditCostResultDetailForm.SpecialAuditCost::getCost).sum());
        return result;
    }

    private AccountRecord generateRecord(String companyName, String telephone, CostResultDetailForm costResultDetail) {
        Double totalCost = 0D;
        if (costResultDetail.getConsultCost() != null) {
            totalCost += costResultDetail.getConsultCost().getTotalCost();
        }
        if (costResultDetail.getIpCost() != null) {
            totalCost += costResultDetail.getIpCost().getTotalCost();
        }
        if (costResultDetail.getOtherCost() != null) {
            totalCost += costResultDetail.getOtherCost().getTotalCost();
        }
        if (costResultDetail.getAnnualAuditCost() != null) {
            totalCost += costResultDetail.getAnnualAuditCost().getTotalCost();
        }
        if (costResultDetail.getSpecialAuditCost() != null) {
            totalCost += costResultDetail.getSpecialAuditCost().getTotalCost();
        }
        AccountRecord record = new AccountRecord();
        record.setCompanyName(companyName);
        record.setTelephone(telephone);
        if (costResultDetail.getConsultCost() == null) {
            record.setConsultCost(0D);
        } else {
            record.setConsultCost(costResultDetail.getConsultCost().getTotalCost());
        }
        if (costResultDetail.getIpCost() == null) {
            record.setIpCost(0D);
        } else {
            record.setIpCost(costResultDetail.getIpCost().getTotalCost());
        }
        if (costResultDetail.getOtherCost() == null) {
            record.setOtherCost(0D);
        } else {
            record.setOtherCost(costResultDetail.getOtherCost().getTotalCost());
        }
        if (costResultDetail.getAnnualAuditCost() == null) {
            record.setAnnualAuditCost(0D);
        } else {
            record.setAnnualAuditCost(costResultDetail.getAnnualAuditCost().getTotalCost());
        }
        if (costResultDetail.getSpecialAuditCost() == null) {
            record.setSpecialAuditCost(0D);
        } else {
            record.setSpecialAuditCost(costResultDetail.getSpecialAuditCost().getTotalCost());
        }
        record.setTotalCost(totalCost);
        record.setDetails(JsonUtils.toJson(costResultDetail));
        record.setAccountTime(new Date());
        return record;
    }

    private String generateDetails(CostResultDetailForm costResultDetail) {
        StringBuilder sb = new StringBuilder();
        sb.append("咨询费用：");
        sb.append(costResultDetail.getConsultCost().getTotalCost());
        sb.append("元；知识产权费用：");
        sb.append(costResultDetail.getIpCost().getTotalCost());
        sb.append("元；其他费用：");
        sb.append(costResultDetail.getOtherCost().getTotalCost());
        sb.append("元；年度审计报告费用：");
        sb.append(costResultDetail.getAnnualAuditCost().getTotalCost());
        sb.append("；专项审计报告费用：");
        sb.append(costResultDetail.getSpecialAuditCost().getTotalCost());
        sb.append("元");
        return sb.toString();
    }

    private Boolean sendResultSms(String telephone, Double totalCost, CostResultDetailForm costResultDetail) {
        Map<String, Object> variates = new HashMap<>();
        variates.put("zfy", totalCost);
        if (costResultDetail.getConsultCost() != null) {
            variates.put("zxfy", costResultDetail.getConsultCost().getTotalCost());
        } else {
            variates.put("zxfy", 0);
        }
        if (costResultDetail.getIpCost() != null) {
            variates.put("zscqfy", costResultDetail.getIpCost().getTotalCost());
        } else {
            variates.put("zscqfy", 0);
        }
        if (costResultDetail.getOtherCost() != null) {
            variates.put("qtfy", costResultDetail.getOtherCost().getTotalCost());
        } else {
            variates.put("qtfy", 0);
        }
        if (costResultDetail.getAnnualAuditCost() != null) {
            variates.put("ndsjbgfy", costResultDetail.getAnnualAuditCost().getTotalCost());
        } else {
            variates.put("ndsjbgfy", 0);
        }
        if (costResultDetail.getSpecialAuditCost() != null) {
            variates.put("zxsjbgfy", costResultDetail.getSpecialAuditCost().getTotalCost());
        } else {
            variates.put("zxsjbgfy", 0);
        }
        variates.put("lxdh", LXDH);
        return smsSendService.singleSend(telephone, "gxfyhs", variates);
    }

}
