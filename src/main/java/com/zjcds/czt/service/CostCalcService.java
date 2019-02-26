package com.zjcds.czt.service;

import com.zjcds.czt.domain.CostAccountData;
import com.zjcds.czt.domain.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luokp on 2019/1/2.
 */
@Service
public class CostCalcService {

    @Autowired
    private CostAccountData costAccountData;

    public Double calcHighFieldCost(HighField highField) {
        switch (highField) {
            case DZXX:
                return costAccountData.getHighFieldCost().get(0);
            case SWYXYY:
                return costAccountData.getHighFieldCost().get(1);
            case HKHT:
                return costAccountData.getHighFieldCost().get(2);
            case XCL:
                return costAccountData.getHighFieldCost().get(3);
            case GXJSFW:
                return costAccountData.getHighFieldCost().get(4);
            case XNYYJN:
                return costAccountData.getHighFieldCost().get(5);
            case ZYYHJ:
                return costAccountData.getHighFieldCost().get(6);
            case XJZZYZDH:
                return costAccountData.getHighFieldCost().get(7);
            case QT:
                return costAccountData.getHighFieldCost().get(8);
            default:
                return 0D;
        }
    }

    public Double calcFinancialGrowthCost(FinancialGrowth financialGrowth) {
        switch (financialGrowth) {
            case A:
                return costAccountData.getFinancialGrowthCost().get(0);
            case B:
                return costAccountData.getFinancialGrowthCost().get(1);
            case C:
                return costAccountData.getFinancialGrowthCost().get(2);
            case D:
                return costAccountData.getFinancialGrowthCost().get(3);
            default:
                return 0D;
        }
    }

    public Double calcRecentYearIncomingCost(RecentYearIncoming recentYearIncoming) {
        switch (recentYearIncoming) {
            case A:
                return costAccountData.getRecentYearIncomingCost().get(0);
            case B:
                return costAccountData.getRecentYearIncomingCost().get(1);
            case C:
                return costAccountData.getRecentYearIncomingCost().get(2);
            case D:
                return costAccountData.getRecentYearIncomingCost().get(3);
            case E:
                return costAccountData.getRecentYearIncomingCost().get(4);
            case F:
                return costAccountData.getRecentYearIncomingCost().get(5);
            case G:
                return costAccountData.getRecentYearIncomingCost().get(6);
            default:
                return 0D;
        }
    }

    public Double calcIPCost(IPType ipType, Integer quantity) {
        switch (ipType) {
            case FMZL:
                return costAccountData.getIpCost().get(0) * quantity;
            case SYXXZL:
                return costAccountData.getIpCost().get(1) * quantity;
            case WGZL:
                return costAccountData.getIpCost().get(2) * quantity;
            case RJZZQ:
                return costAccountData.getIpCost().get(3) * quantity;
            case JCDLBTSJ:
                return costAccountData.getIpCost().get(4) * quantity;
            case FMZL85:
                return costAccountData.getIpCost().get(5) * quantity;
            case SYXXZL85:
                return costAccountData.getIpCost().get(6) * quantity;
            case WGZL85:
                return costAccountData.getIpCost().get(7) * quantity;
            case FMZL70:
                return costAccountData.getIpCost().get(8) * quantity;
            case SYXXZL70:
                return costAccountData.getIpCost().get(9) * quantity;
            case WGZL70:
                return costAccountData.getIpCost().get(10) * quantity;
            default:
                return 0D;
        }
    }

    public Double calcOtherCost(OtherCostType otherCostType, Integer quantity) {
        switch (otherCostType) {
            case RJPCBG:
                return costAccountData.getOtherCost().get(0) * quantity;
            case CXBG:
                return costAccountData.getOtherCost().get(1) * quantity;
            case CPJCBG:
                return costAccountData.getOtherCost().get(2) * quantity;
            case CWYFFDDJZ:
                return costAccountData.getOtherCost().get(3) * quantity;
            case SJXCP:
                return costAccountData.getOtherCost().get(4) * quantity;
            default:
                return 0D;
        }
    }

    public Double calcAnnualAuditCost(Double amount) {
        if (amount <= 200) {
            return costAccountData.getAnnualAuditCost().get(0);
        } else if (amount <= 300) {
            return costAccountData.getAnnualAuditCost().get(1);
        } else if (amount <= 600) {
            return costAccountData.getAnnualAuditCost().get(2);
        } else if (amount <= 1000) {
            return costAccountData.getAnnualAuditCost().get(3);
        } else if (amount <= 2000) {
            return costAccountData.getAnnualAuditCost().get(4);
        } else if (amount <= 4000) {
            return costAccountData.getAnnualAuditCost().get(5);
        } else if (amount <= 6000) {
            return costAccountData.getAnnualAuditCost().get(6);
        } else if (amount <= 8000) {
            return costAccountData.getAnnualAuditCost().get(7);
        } else if (amount <= 10000) {
            return costAccountData.getAnnualAuditCost().get(8);
        } else {
            return amount * 100 * costAccountData.getAnnualAuditCost().get(9);
        }
    }

    public Double calcSpecialAuditCost(Double incoming, Double managerCost, Double rdCost) {
        Double rdSa;
        Double incomingSa;
        if (rdCost == null || rdCost == 0D) {
            rdCost = managerCost / 2;
        }
        if (incoming <= 500) {
            if (rdCost / incoming >= 0.05) {
                rdSa = calcRdSaRuleA(incoming, rdCost);
            } else {
                rdSa = calcRdSaRuleB(incoming, rdCost);
            }
        } else if (incoming < 2000) {
            if (rdCost / incoming >= 0.04) {
                rdSa = calcRdSaRuleA(incoming, rdCost);
            } else {
                rdSa = calcRdSaRuleB(incoming, rdCost);
            }
        } else {
            if (rdCost / incoming >= 0.03) {
                rdSa = calcRdSaRuleA(incoming, rdCost);
            } else {
                rdSa = calcRdSaRuleB(incoming, rdCost);
            }
        }
        incomingSa = calcIncomingSa(incoming);
        return rdSa + incomingSa;
    }

    public Double calcRdSa(Double incoming, Double managerCost, Double rdCost){
        if (rdCost == null || rdCost == 0D) {
            rdCost = managerCost / 2;
        }
        if (incoming <= 500) {
            if (rdCost / incoming >= 0.05) {
                return calcRdSaRuleA(incoming, rdCost);
            } else {
                return calcRdSaRuleB(incoming, rdCost);
            }
        } else if (incoming < 2000) {
            if (rdCost / incoming >= 0.04) {
                return calcRdSaRuleA(incoming, rdCost);
            } else {
                return calcRdSaRuleB(incoming, rdCost);
            }
        } else {
            if (rdCost / incoming >= 0.03) {
                return calcRdSaRuleA(incoming, rdCost);
            } else {
                return calcRdSaRuleB(incoming, rdCost);
            }
        }
    }

    public Double calcRdSaRuleA(Double incoming, Double rdCost) {
        if (rdCost <= 100) {
            return costAccountData.getRaACost().get(0);
        } else if (rdCost <= 300) {
            return rdCost * 100 * costAccountData.getRaACost().get(1);
        } else if (rdCost <= 600) {
            return rdCost * 100 * costAccountData.getRaACost().get(2);
        } else if (rdCost <= 1000) {
            return rdCost * 100 * costAccountData.getRaACost().get(3);
        } else if (rdCost <= 2000) {
            return rdCost * 100 * costAccountData.getRaACost().get(4);
        } else if (rdCost <= 4000) {
            return rdCost * 100 * costAccountData.getRaACost().get(5);
        } else if (rdCost <= 6000) {
            return rdCost * 100 * costAccountData.getRaACost().get(6);
        } else if (rdCost <= 8000) {
            return rdCost * 100 * costAccountData.getRaACost().get(7);
        } else if (rdCost <= 10000) {
            return rdCost * 100 * costAccountData.getRaACost().get(8);
        } else {
            return rdCost * 100 * costAccountData.getRaACost().get(9);
        }
    }

    public Double calcRdSaRuleB(Double incoming, Double rdCost) {
        if (incoming <= 50) {
            return costAccountData.getRaBCost().get(0);
        } else if (incoming <= 180) {
            return costAccountData.getRaBCost().get(1);
        } else if (incoming <= 300) {
            return incoming * 100 * costAccountData.getRaBCost().get(2);
        } else if (incoming <= 600) {
            return incoming * 100 * costAccountData.getRaBCost().get(3);
        } else if (incoming <= 1000) {
            return incoming * 100 * costAccountData.getRaBCost().get(4);
        } else if (incoming <= 2000) {
            return incoming * 100 * costAccountData.getRaBCost().get(5);
        } else if (incoming <= 4000) {
            return incoming * 100 * costAccountData.getRaBCost().get(6);
        } else if (incoming <= 6000) {
            return incoming * 100 * costAccountData.getRaBCost().get(7);
        } else if (incoming <= 8000) {
            return incoming * 100 * costAccountData.getRaBCost().get(8);
        } else if (incoming <= 10000) {
            return incoming * 100 * costAccountData.getRaBCost().get(9);
        } else {
            return incoming * 100 * costAccountData.getRaBCost().get(10);
        }
    }

    public Double calcIncomingSa(Double incoming) {
        if (incoming <= 200) {
            return costAccountData.getIncomingCost().get(0);
        } else if (incoming <= 300) {
            return costAccountData.getIncomingCost().get(1);
        } else if (incoming <= 600) {
            return costAccountData.getIncomingCost().get(2);
        } else if (incoming <= 1000) {
            return costAccountData.getIncomingCost().get(3);
        } else if (incoming <= 2000) {
            return costAccountData.getIncomingCost().get(4);
        } else if (incoming <= 4000) {
            return costAccountData.getIncomingCost().get(5);
        } else if (incoming <= 6000) {
            return costAccountData.getIncomingCost().get(6);
        } else if (incoming <= 8000) {
            return costAccountData.getIncomingCost().get(7);
        } else if (incoming <= 10000) {
            return costAccountData.getIncomingCost().get(8);
        } else {
            return incoming * 100 * costAccountData.getIncomingCost().get(9);
        }
    }

}
