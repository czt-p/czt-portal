package com.zjcds.czt.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luokp on 2019/1/22.
 */
@Getter
@Setter
@Component
@PropertySource("classpath:costAccount.properties")
@ConfigurationProperties(prefix = "costAccount")
public class CostAccountData {

    private List<Double> highFieldCost;

    private List<Double> financialGrowthCost;

    private List<Double> recentYearIncomingCost;

    private List<Double> ipCost;

    private List<Double> otherCost;

    private List<Double> annualAuditCost;

    private List<Double> raACost;

    private List<Double> raBCost;

    private List<Double> incomingCost;

}
