package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.resolution.CalculationRule;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByNormRule")
public class VolumeByNormCalculationRule implements CalculationRule {

    @Override
    public long volume(@NonNull final RoomProperties roomProperties,
                       @NonNull final AccountingPointProperty accountingPointProperty,
                       @NonNull final ServicePartProperty servicePartProperty) {
        return 0;
//        int coefficientNormValue = item.getCoefficientNormValue();
//
//        long normIndex = item.getNormIndex();
//
//        long durationByDays = durationCalculator.getDurationByDays(item);
//
//        long daysOfCalculationPeriod = durationCalculator.daysOfCalculationPeriod(item);
//
//        int normValue = Optional.ofNullable(item.getKeyNormValue()).orElse(new KeyNormValue()).getNormValue();
//
//        return (normValue * coefficientNormValue * normIndex * durationByDays / daysOfCalculationPeriod);
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_NORM;
    }
}
