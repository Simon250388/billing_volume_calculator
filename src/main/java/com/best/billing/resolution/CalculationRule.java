package com.best.billing.resolution;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.NonNull;

public interface CalculationRule {
    long volume(
            @NonNull final RoomProperties roomProperties,
            @NonNull final AccountingPointProperty accountingPointProperty,
            @NonNull final ServicePartProperty servicePartProperty);

    CalculationMethod getCalculationMethod();
}
