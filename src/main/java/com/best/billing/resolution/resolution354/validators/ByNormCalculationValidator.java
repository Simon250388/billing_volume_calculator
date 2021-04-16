package com.best.billing.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.resolution.CalculationValidator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ByNormCalculationValidator")
@Slf4j
public class ByNormCalculationValidator implements CalculationValidator {
    @Override
    public boolean canCalculateVolume(@NonNull final CalculationSettings calculationSettings,
                                      @NonNull final RoomProperties roomProperties,
                                      @NonNull final AccountingPointProperty accountingPointProperty,
                                      @NonNull final ServicePartProperty servicePartProperty) {
        MeterState meterState = accountingPointProperty.getMeterState();
        return accountingPointProperty.isServiceActive()
                && meterState != MeterState.ACTIVE;
    }
}
