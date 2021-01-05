package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.resolution354.CalculationValidator;

import java.util.Optional;

public class ByNormCalculationValidatorImpl implements CalculationValidator {
    @Override
    public boolean canCalculateVolume(CalculationItem item) {
        Optional<AccountingPointMeterState> accountingPointMeterState = Optional.of(item.getStabPeriod().getAccountingPointMeterState());
        MeterState meterState = accountingPointMeterState.orElse(new AccountingPointMeterState()).getMeterState();
        return item.getStabPeriod().getAccountingPointServiceState().isActive()
                && meterState != MeterState.ACTIVE;
    }

    @Override
    public boolean isCanCalculateVolumeFact(CalculationItem item) {
        return false;
    }
}
