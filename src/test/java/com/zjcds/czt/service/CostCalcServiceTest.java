package com.zjcds.czt.service;

import com.zjcds.czt.SpringBootTestSupport;
import com.zjcds.czt.domain.enums.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author luokp on 2019/1/13.
 */
public class CostCalcServiceTest extends SpringBootTestSupport {

    @Autowired
    private CostCalcService costCalcService;

    @Test
    public void testCalcHighFieldCost() {
        assertEquals((Double) 40000D, costCalcService.calcHighFieldCost(HighField.DZXX));
        assertEquals((Double) 50000D, costCalcService.calcHighFieldCost(HighField.SWYXYY));
        assertEquals((Double) 60000D, costCalcService.calcHighFieldCost(HighField.HKHT));
        assertEquals((Double) 50000D, costCalcService.calcHighFieldCost(HighField.XCL));
        assertEquals((Double) 45000D, costCalcService.calcHighFieldCost(HighField.GXJSFW));
        assertEquals((Double) 60000D, costCalcService.calcHighFieldCost(HighField.XNYYJN));
        assertEquals((Double) 60000D, costCalcService.calcHighFieldCost(HighField.ZYYHJ));
        assertEquals((Double) 50000D, costCalcService.calcHighFieldCost(HighField.XJZZYZDH));
        assertEquals((Double) 100000D, costCalcService.calcHighFieldCost(HighField.QT));
    }

    @Test
    public void testCalcFinancialGrowthCost() {
        assertEquals((Double) 5000D, costCalcService.calcFinancialGrowthCost(FinancialGrowth.A));
        assertEquals((Double) 10000D, costCalcService.calcFinancialGrowthCost(FinancialGrowth.B));
        assertEquals((Double) 15000D, costCalcService.calcFinancialGrowthCost(FinancialGrowth.C));
        assertEquals((Double) 30000D, costCalcService.calcFinancialGrowthCost(FinancialGrowth.D));
    }

    @Test
    public void testCalcRecentYearIncomingCost() {
        assertEquals((Double) 10000D, costCalcService.calcRecentYearIncomingCost(RecentYearIncoming.A));
        assertEquals((Double) 5000D, costCalcService.calcRecentYearIncomingCost(RecentYearIncoming.B));
        assertEquals((Double) 5000D, costCalcService.calcRecentYearIncomingCost(RecentYearIncoming.C));
        assertEquals((Double) 10000D, costCalcService.calcRecentYearIncomingCost(RecentYearIncoming.D));
        assertEquals((Double) 20000D, costCalcService.calcRecentYearIncomingCost(RecentYearIncoming.E));
        assertEquals((Double) 30000D, costCalcService.calcRecentYearIncomingCost(RecentYearIncoming.F));
        assertEquals((Double) 50000D, costCalcService.calcRecentYearIncomingCost(RecentYearIncoming.G));
    }

    @Test
    public void testCalcIPCost() {
        assertEquals((Double) 8455D, costCalcService.calcIPCost(IPType.FMZL, 1));
        assertEquals((Double) 2505D, costCalcService.calcIPCost(IPType.SYXXZL, 1));
        assertEquals((Double) 1305D, costCalcService.calcIPCost(IPType.WGZL, 1));
        assertEquals((Double) 900D, costCalcService.calcIPCost(IPType.RJZZQ, 1));
        assertEquals((Double) 2005D, costCalcService.calcIPCost(IPType.JCDLBTSJ, 1));
        assertEquals((Double) 5565D, costCalcService.calcIPCost(IPType.FMZL85, 1));
        assertEquals((Double) 2080D, costCalcService.calcIPCost(IPType.SYXXZL85, 1));
        assertEquals((Double) 880D, costCalcService.calcIPCost(IPType.WGZL85, 1));
        assertEquals((Double) 6075D, costCalcService.calcIPCost(IPType.FMZL70, 1));
        assertEquals((Double) 2155D, costCalcService.calcIPCost(IPType.SYXXZL70, 1));
        assertEquals((Double) 955D, costCalcService.calcIPCost(IPType.WGZL70, 1));
    }

    @Test
    public void testCalcOtherCost() {
        assertEquals((Double) 4000D, costCalcService.calcOtherCost(OtherCostType.RJPCBG, 2));
        assertEquals((Double) 1700D, costCalcService.calcOtherCost(OtherCostType.CXBG, 2));
        assertEquals((Double) 7000D, costCalcService.calcOtherCost(OtherCostType.CPJCBG, 2));
        assertEquals((Double) 16000D, costCalcService.calcOtherCost(OtherCostType.CWYFFDDJZ, 2));
        assertEquals((Double) 18000D, costCalcService.calcOtherCost(OtherCostType.SJXCP, 2));
    }

    @Test
    public void testCalcAnnualAuditCost() {
        assertEquals((Double) 3000D, costCalcService.calcAnnualAuditCost(200D));
        assertEquals((Double) 3500D, costCalcService.calcAnnualAuditCost(300D));
        assertEquals((Double) 4500D, costCalcService.calcAnnualAuditCost(600D));
        assertEquals((Double) 5800D, costCalcService.calcAnnualAuditCost(1000D));
        assertEquals((Double) 7000D, costCalcService.calcAnnualAuditCost(2000D));
        assertEquals((Double) 8500D, costCalcService.calcAnnualAuditCost(4000D));
        assertEquals((Double) 11000D, costCalcService.calcAnnualAuditCost(6000D));
        assertEquals((Double) 12500D, costCalcService.calcAnnualAuditCost(8000D));
        assertEquals((Double) 14000D, costCalcService.calcAnnualAuditCost(10000D));
        assertEquals((Double) 15400D, costCalcService.calcAnnualAuditCost(11000D));
    }

    @Test
    public void testCalcRdSaRuleA() {
        assertEquals((Double) 3000D, costCalcService.calcRdSaRuleA(10000D, 100D));
        assertEquals((Double) 9000D, costCalcService.calcRdSaRuleA(10000D, 300D));
        assertEquals((Double) 9000D, costCalcService.calcRdSaRuleA(10000D, 600D));
        assertEquals((Double) 10000D, costCalcService.calcRdSaRuleA(10000D, 1000D));
        assertEquals((Double) 17000D, costCalcService.calcRdSaRuleA(10000D, 2000D));
        assertEquals((Double) 15200D, costCalcService.calcRdSaRuleA(10000D, 4000D));
        assertEquals((Double) 14400D, costCalcService.calcRdSaRuleA(10000D, 6000D));
        assertEquals((Double) 16000D, costCalcService.calcRdSaRuleA(10000D, 8000D));
        assertEquals((Double) 18000D, costCalcService.calcRdSaRuleA(10000D, 10000D));
        assertEquals((Double) 15400D, costCalcService.calcRdSaRuleA(10000D, 11000D));
    }

    @Test
    public void testCalcRdSaRuleB() {
        assertEquals((Double) 3000D, costCalcService.calcRdSaRuleB(50D, 100D));
        assertEquals((Double) 3000D, costCalcService.calcRdSaRuleB(180D, 100D));
        assertEquals((Double) 5400D, costCalcService.calcRdSaRuleB(300D, 100D));
        assertEquals((Double) 7200D, costCalcService.calcRdSaRuleB(600D, 100D));
        assertEquals((Double) 7500D, costCalcService.calcRdSaRuleB(1000D, 100D));
        assertEquals((Double) 8400D, costCalcService.calcRdSaRuleB(2000D, 100D));
        assertEquals((Double) 9200D, costCalcService.calcRdSaRuleB(4000D, 100D));
        assertEquals((Double) 9000D, costCalcService.calcRdSaRuleB(6000D, 100D));
        assertEquals((Double) 11200D, costCalcService.calcRdSaRuleB(8000D, 100D));
        assertEquals((Double) 12800D, costCalcService.calcRdSaRuleB(10000D, 100D));
        assertEquals((Double) 14080D, costCalcService.calcRdSaRuleB(11000D, 100D));
    }

    @Test
    public void testCalcIncomingSa() {
        assertEquals((Double) 3000D, costCalcService.calcIncomingSa(200D));
        assertEquals((Double) 3500D, costCalcService.calcIncomingSa(300D));
        assertEquals((Double) 4500D, costCalcService.calcIncomingSa(600D));
        assertEquals((Double) 5800D, costCalcService.calcIncomingSa(1000D));
        assertEquals((Double) 7000D, costCalcService.calcIncomingSa(2000D));
        assertEquals((Double) 8500D, costCalcService.calcIncomingSa(4000D));
        assertEquals((Double) 11000D, costCalcService.calcIncomingSa(6000D));
        assertEquals((Double) 12500D, costCalcService.calcIncomingSa(8000D));
        assertEquals((Double) 14000D, costCalcService.calcIncomingSa(10000D));
        assertEquals((Double) 15400D, costCalcService.calcIncomingSa(11000D));
    }

    @Test
    public void testCalcSpecialAuditCost() {
        assertEquals((Double) (6000D + 4500D), costCalcService.calcSpecialAuditCost(500D, 0D, 5D));
        assertEquals((Double) (6000D + 4500D), costCalcService.calcSpecialAuditCost(500D, 10D, 0D));
        assertEquals((Double) (3000D + 4500D), costCalcService.calcSpecialAuditCost(500D, 0D, 25D));
        assertEquals((Double) (3000D + 4500D), costCalcService.calcSpecialAuditCost(500D, 50D, 0D));
        assertEquals((Double) (12800D + 14000D), costCalcService.calcSpecialAuditCost(10000D, 0D, 100D));
        assertEquals((Double) (12800D + 14000D), costCalcService.calcSpecialAuditCost(10000D, 200D, 0D));
        assertEquals((Double) (6000D + 14000D), costCalcService.calcSpecialAuditCost(10000D, 0D, 400D));
        assertEquals((Double) (6000D + 14000D), costCalcService.calcSpecialAuditCost(10000D, 800D, 0D));
        assertEquals((Double) (25600D + 28000D), costCalcService.calcSpecialAuditCost(20000D, 0D, 200D));
        assertEquals((Double) (25600D + 28000D), costCalcService.calcSpecialAuditCost(20000D, 400D, 0D));
        assertEquals((Double) (9000D + 28000D), costCalcService.calcSpecialAuditCost(20000D, 0D, 600D));
        assertEquals((Double) (9000D + 28000D), costCalcService.calcSpecialAuditCost(20000D, 1200D, 0D));
    }

}
