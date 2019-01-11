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
            case DAXX:
                return 43000D;
            case SWYSYY:
                return 43000D;
            case HKHT:
                return 53000D;
            case XCL:
                return 48000D;
            case GXJSFW:
                return 53000D;

            case XNYYJN:
                return 53000D;

            case ZYYHJ:
                return 55000D;

            case XJZZYZDH:
                return 53000D;

            default:
                return 0D;
        }
    }

    public Double calcFinancialGrowthCost(FinancialGrowth financialGrowth) {
        switch (financialGrowth) {
            case A:
                return 0D;

            case B:
                return 8000D;

            case C:
                return 11000D;

            case D:
                return 18000D;

            case E:
                return 28000D;

            default:
                return 0D;
        }
    }

    public Double calcRecentYearIncomingCost(RecentYearIncoming recentYearIncoming) {
        switch (recentYearIncoming) {
            case A:
                return 0D;

            case B:
                return 5000D;

            case C:
                return 80000D;

            case D:
                return 11000D;

            case E:
                return 18000D;

            case F:
                return 33000D;

            default:
                return 0D;
        }
    }

    public Double calcIPCost(IPType ipType, Integer quantity) {
        switch (ipType) {
            case FMZL:
                return 5500D * quantity;

            case SYXXZL:
                return 2300D * quantity;

            case WGZL:
                return 1000D * quantity;

            case RJZZQ:
                return 1000D * quantity;

            default:
                return 0D;
        }
    }

    public Double calcOtherCost(OtherCostType otherCostType, Integer quantity) {
        switch (otherCostType) {
            case RJPCBG:
                return 3000D * quantity;

            case CXBG:
                return 1600D * quantity;

            case CPJCBG:
                return 3200D * quantity;

            case CWGFFD:
                return 8000D * quantity;

            default:
                return 0D;
        }
    }

    public Double calcAnnualAuditCost(Double amount) {
        if (amount <= 50) {
            return 2000D;
        } else if (amount <= 180) {
            return 3000D;
        } else if (amount <= 300) {
            return amount * 100 * 0.17;
        } else if (amount <= 600) {
            return amount * 100 * 0.10;
        } else if (amount <= 1000) {
            return amount * 100 * 0.07;
        } else if (amount <= 2000) {
            return amount * 100 * 0.04;
        } else if (amount <= 4000) {
            return amount * 100 * 0.028;
        } else if (amount <= 6000) {
            return amount * 100 * 0.015;
        } else if (amount <= 8000) {
            return amount * 100 * 0.0145;
        } else if (amount <= 10000) {
            return amount * 100 * 0.013;
        } else {
            return amount * 100 * 0.011;
        }
    }

    public Double calcSpecialAuditCost(Double incoming, Double managerCost, Double raCost) {
        Double raCostFY;
        Double incomingCost;
        if (raCost == null || raCost == 0D) {
            raCost = managerCost / 2;
        }
        if (raCost / incoming >= 0.05) {
            raCostFY = calcRACostRuleA(incoming, raCost);
        } else {
            raCostFY = calcRACostRuleB(incoming, raCost);
        }
        incomingCost = calcIncomingCost(incoming);
        return raCostFY + incomingCost;
    }

    private Double calcRACostRuleA(Double incoming, Double raCost) {
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

    private Double calcRACostRuleB(Double incoming, Double raCost) {
        if (incoming <= 50) {
            return 3000D;
        } else if (incoming < 180) {
            return 3000D;
        } else if (incoming <= 300) {
            return raCost * 100 * 0.18;
        } else if (incoming <= 600) {
            return raCost * 100 * 0.12;
        } else if (incoming <= 1000) {
            return raCost * 100 * 0.075;
        } else if (incoming <= 2000) {
            return raCost * 100 * 0.042;
        } else if (incoming <= 4000) {
            return raCost * 100 * 0.023;
        } else if (incoming <= 6000) {
            return raCost * 100 * 0.015;
        } else if (incoming <= 8000) {
            return raCost * 100 * 0.014;
        } else if (incoming <= 10000) {
            return raCost * 100 * 0.0128;
        } else {
            return raCost * 100 * 0.0128;
        }
    }

    private Double calcIncomingCost(Double incoming) {
        if (incoming <= 50) {
            return 3800D;
        } else if (incoming < 180) {
            return 3800D;
        } else if (incoming <= 300) {
            return incoming * 100 * 0.20;
        } else if (incoming <= 600) {
            return incoming * 100 * 0.12;
        } else if (incoming <= 1000) {
            return incoming * 100 * 0.075;
        } else if (incoming <= 2000) {
            return incoming * 100 * 0.044;
        } else if (incoming <= 4000) {
            return incoming * 100 * 0.024;
        } else if (incoming <= 6000) {
            return incoming * 100 * 0.015;
        } else if (incoming <= 8000) {
            return incoming * 100 * 0.0142;
        } else if (incoming <= 10000) {
            return incoming * 100 * 0.0128;
        } else {
            return incoming * 100 * 0.0128;
        }
    }

}
