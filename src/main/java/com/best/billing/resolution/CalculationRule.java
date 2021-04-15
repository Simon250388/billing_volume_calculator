package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.volumecalculator.model.CalculationMethod;

public interface CalculationRule {
    long volume(
            CalculationSettings calculationSettings,
            RoomProperties roomProperties,
            AccountingPointProperty accountingPointProperty,
            ServicePartProperty servicePartProperty
           );

    CalculationMethod getCalculationMethod();
}
