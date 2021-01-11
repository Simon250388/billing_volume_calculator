package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.CalculationValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("ByNormCalculationValidator")
public class ByNormCalculationValidator implements CalculationValidator {
    @Override
    public boolean canCalculateVolume(CalculationItem item) {
        Optional<AccountingPointMeterState> accountingPointMeterState = Optional.of(item.getStabPeriod().getAccountingPointMeterState());
        MeterState meterState = accountingPointMeterState.orElse(new AccountingPointMeterState()).getMeterState();
        return item.getStabPeriod().getAccountingPointServiceState().isActive()
                && meterState != MeterState.ACTIVE;
    }
}
