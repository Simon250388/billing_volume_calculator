package com.best.billing.resolution;

import com.best.billing.departmen.customer.*;

import java.util.List;

public interface CalculationRule {
    List<CalculationResult> volume(
            CalculationSettings calculationSettings,
            RoomProperties roomProperties,
            AccountingPointProperty accountingPointProperty,
            ServicePartProperty servicePartProperty
    );
}
