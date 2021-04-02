package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperties;

public interface CalculationValidator {
    boolean canCalculateVolume(AccountingPointProperties accountingPointProperties);
}
