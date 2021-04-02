package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperties;

import java.util.List;

public interface Resolution {
    List<CalculationRule> getCalculationRulesForEvent(AccountingPointProperties accountingPointProperties);
}
