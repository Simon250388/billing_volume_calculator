package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.volumecalculator.model.CalculationMethod;

public interface CalculationRule {
    long volume(AccountingPointProperties accountingPointProperties);
    CalculationMethod getCalculationMethod();
}
