package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;

import java.util.List;

public interface Resolution {
    List<CalculationRule> getCalculationRulesForEvent(
            CalculationSettings calculationSettings,
            RoomProperties roomProperties,
            AccountingPointProperty accountingPointProperty,
            ServicePartProperty servicePartProperty);
}
