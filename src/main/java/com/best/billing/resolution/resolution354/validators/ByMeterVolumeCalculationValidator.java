package com.best.billing.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.resolution.CalculationValidator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ByMeterVolumeCalculationValidator")
public class ByMeterVolumeCalculationValidator implements CalculationValidator {

    @Override
    public boolean canCalculateVolume(@NonNull final AccountingPointProperties accountingPointProperties) {
        return accountingPointProperties.isServiceActive()
                && accountingPointProperties.getMeterState() == MeterState.ACTIVE
                && accountingPointProperties.isMeterValuesProvide();
    }
}
