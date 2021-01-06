package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.resolution354.CalculationValidator;

import java.util.Optional;

public class ByAvgNormCalculationValidator implements CalculationValidator {
    @Override
    public boolean canCalculateVolume(CalculationItem item) {
        return item.getStabPeriod().getAccountingPointServiceState().isActive() &&
                item.getStabPeriod().getAccountingPointMeterState().getMeterState() == MeterState.ACTIVE
                && item.meterValuesIsProvide() == false
                && Optional.ofNullable(item.getAccountingPointServiceAvgVolume()).isPresent() == false;
    }

    @Override
    public boolean isCanCalculateVolumeFact(CalculationItem item) {
        return false;
    }
}
