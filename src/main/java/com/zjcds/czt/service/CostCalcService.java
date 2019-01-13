package com.zjcds.czt.service;

import com.zjcds.czt.domain.enums.*;
import org.springframework.stereotype.Service;

/**
 * @author luokp on 2019/1/2.
 */
@Service
public class CostCalcService {

    public Double calcHighFieldCost(HighField highField) {
        switch (highField) {
            case DZXX:
                return 40000D;
            case SWYXYY:
                return 50000D;
            case HKHT:
                return 60000D;
            case XCL:
                return 50000D;
            case GXJSFW:
                return 45000D;
            case XNYYJN:
                return 60000D;
            case ZYYHJ:
                return 60000D;
            case XJZZYZDH:
                return 50000D;
            case QT:
                return 100000D;
            default:
                return 0D;
        }
    }

    public Double calcFinancialGrowthCost(FinancialGrowth financialGrowth) {
        switch (financialGrowth) {
            case A:
                return 5000D;
            case B:
                return 10000D;
            case C:
                return 15000D;
            case D:
                return 30000D;
            default:
                return 0D;
        }
    }

    public Double calcRecentYearIncomingCost(RecentYearIncoming recentYearIncoming) {
        switch (recentYearIncoming) {
            case A:
                return 10000D;
            case B:
                return 5000D;
            case C:
                return 5000D;
            case D:
                return 10000D;
            case E:
                return 20000D;
            case F:
                return 30000D;
            case G:
                return 50000D;
            default:
                return 0D;
        }
    }

    public Double calcIPCost(IPType ipType, Integer quantity) {
        switch (ipType) {
            case FMZL:
                return (5000D + 3455D) * quantity;
            case SYXXZL:
                return (2000D + 505D) * quantity;
            case WGZL:
                return (800D + 505D) * quantity;
            case RJZZQ:
                return 900D * quantity;
            case JCDLBTSJ:
                return 0D * quantity;
            case FMZL85:
                return (5000D + 565D) * quantity;
            case SYXXZL85:
                return (2000D + 80D) * quantity;
            case WGZL85:
                return (800D + 80D) * quantity;
            case FMZL70:
                return (5000D + 1075D) * quantity;
            case SYXXZL70:
                return (2000D + 155D) * quantity;
            case WGZL70:
                return (800D + 155D) * quantity;
            default:
                return 0D;
        }
    }

    public Double calcOtherCost(OtherCostType otherCostType, Integer quantity) {
        switch (otherCostType) {
            case RJPCBG:
                return 2000D * quantity;
            case CXBG:
                return 850D * quantity;
            case CPJCBG:
                return 3500D * quantity;
            case CWYFFDDJZ:
                return 8000D * quantity;
            case SJXCP:
                return 0D * quantity;
            default:
                return 0D;
        }
    }

    public Double calcAnnualAuditCost(Double amount) {
        if (amount <= 200) {
            return 3000D;
        } else if (amount <= 300) {
            return 3500D;
        } else if (amount <= 600) {
            return 4500D;
        } else if (amount <= 1000) {
            return 5800D;
        } else if (amount <= 2000) {
            return 7000D;
        } else if (amount <= 4000) {
            return 8500D;
        } else if (amount <= 6000) {
            return 11000D;
        } else if (amount <= 8000) {
            return 12500D;
        } else if (amount <= 10000) {
            return 14000D;
        } else {
            return amount * 100 * 0.014;
        }
    }

    public Double calcSpecialAuditCost(Double incoming, Double managerCost, Double raCost) {
        Double raCostFY;
        Double incomingCost;
        if (raCost == null || raCost == 0D) {
            raCost = managerCost / 2;
        }
        if (incoming <= 500) {
            if (raCost / incoming >= 0.05) {
                raCostFY = calcRACostRuleA(incoming, raCost);
            } else {
                raCostFY = calcRACostRuleB(incoming, raCost);
            }
        } else if (incoming < 2000) {
            if (raCost / incoming >= 0.04) {
                raCostFY = calcRACostRuleA(incoming, raCost);
            } else {
                raCostFY = calcRACostRuleB(incoming, raCost);
            }
        } else {
            if (raCost / incoming >= 0.03) {
                raCostFY = calcRACostRuleA(incoming, raCost);
            } else {
                raCostFY = calcRACostRuleB(incoming, raCost);
            }
        }
        incomingCost = calcIncomingCost(incoming);
        return raCostFY + incomingCost;
    }

    public Double calcRACostRuleA(Double incoming, Double raCost) {
        if (raCost <= 100) {
            return 3000D;
        } else if (raCost <= 300) {
            return raCost * 100 * 0.3;
        } else if (raCost <= 600) {
            return raCost * 100 * 0.15;
        } else if (raCost <= 1000) {
            return raCost * 100 * 0.1;
        } else if (raCost <= 2000) {
            return raCost * 100 * 0.085;
        } else if (raCost <= 4000) {
            return raCost * 100 * 0.038;
        } else if (raCost <= 6000) {
            return raCost * 100 * 0.024;
        } else if (raCost <= 8000) {
            return raCost * 100 * 0.02;
        } else if (raCost <= 10000) {
            return raCost * 100 * 0.018;
        } else {
            return raCost * 100 * 0.014;
        }
    }

    public Double calcRACostRuleB(Double incoming, Double raCost) {
        if (incoming <= 50) {
            return 3000D;
        } else if (incoming <= 180) {
            return 3000D;
        } else if (incoming <= 300) {
            return incoming * 100 * 0.18;
        } else if (incoming <= 600) {
            return incoming * 100 * 0.12;
        } else if (incoming <= 1000) {
            return incoming * 100 * 0.075;
        } else if (incoming <= 2000) {
            return incoming * 100 * 0.042;
        } else if (incoming <= 4000) {
            return incoming * 100 * 0.023;
        } else if (incoming <= 6000) {
            return incoming * 100 * 0.015;
        } else if (incoming <= 8000) {
            return incoming * 100 * 0.014;
        } else if (incoming <= 10000) {
            return incoming * 100 * 0.0128;
        } else {
            return incoming * 100 * 0.0128;
        }
    }

    public Double calcIncomingCost(Double incoming) {
        if (incoming <= 200) {
            return 3000D;
        } else if (incoming <= 300) {
            return 3500D;
        } else if (incoming <= 600) {
            return 4500D;
        } else if (incoming <= 1000) {
            return 5800D;
        } else if (incoming <= 2000) {
            return 7000D;
        } else if (incoming <= 4000) {
            return 8500D;
        } else if (incoming <= 6000) {
            return 11000D;
        } else if (incoming <= 8000) {
            return 12500D;
        } else if (incoming <= 10000) {
            return 14000D;
        } else {
            return incoming * 100 * 0.014;
        }
    }

}
