package com.best.billing.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.resolution.CalculationValidator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ByNormCalculationValidator")
public class ByNormCalculationValidator implements CalculationValidator {
    @Override
    public boolean canCalculateVolume(@NonNull final AccountingPointProperties accountingPointProperties) {
        MeterState meterState = accountingPointProperties.getMeterState();
        return accountingPointProperties.isServiceActive()
                && meterState != MeterState.ACTIVE;
    }
}
