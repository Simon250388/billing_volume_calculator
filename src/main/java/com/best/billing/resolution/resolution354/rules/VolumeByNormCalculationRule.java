package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.resolution.CalculationRule;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByNormRule")
public class VolumeByNormCalculationRule implements CalculationRule {

    @Override
    public long volume(@NonNull final AccountingPointProperties accountingPointProperties) {
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
