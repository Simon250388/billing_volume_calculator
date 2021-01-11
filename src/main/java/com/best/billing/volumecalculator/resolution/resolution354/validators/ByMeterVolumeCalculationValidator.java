package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.CalculationValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ByMeterVolumeCalculationValidator")
public class ByMeterVolumeCalculationValidator implements CalculationValidator {
    @Override
    public boolean canCalculateVolume(CalculationItem item) {
        return canCalculate(item);
    }

    private boolean canCalculate(CalculationItem item) {
        return item.getStabPeriod().getAccountingPointServiceState().isActive() &&
                item.getStabPeriod().getAccountingPointMeterState().getMeterState() == MeterState.ACTIVE
                && item.meterValuesIsProvide();
    }
}
