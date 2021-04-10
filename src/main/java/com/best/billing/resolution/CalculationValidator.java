package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import lombok.NonNull;

public interface CalculationValidator {
    boolean canCalculateVolume(
            @NonNull final RoomProperties roomProperties,
            @NonNull final AccountingPointProperty accountingPointProperty,
            @NonNull final ServicePartProperty servicePartProperty);
}
