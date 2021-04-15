package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;

public interface CalculationValidator {
    boolean canCalculateVolume(
            CalculationSettings calculationSettings,
            RoomProperties roomProperties,
            AccountingPointProperty accountingPointProperty,
            ServicePartProperty servicePartProperty
           );
}
