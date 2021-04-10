package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;

import java.util.List;

public interface Resolution {
    List<CalculationRule> getCalculationRulesForEvent(
            RoomProperties roomProperties,
            AccountingPointProperty accountingPointProperty,
            ServicePartProperty servicePartProperty);
}
