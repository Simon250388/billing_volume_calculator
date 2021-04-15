package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByNormRule")
public class VolumeByNormCalculationRule implements CalculationRule {

    @Override
    public long volume(@NonNull final CalculationSettings calculationSettings,
                       @NonNull final RoomProperties roomProperties,
                       @NonNull final AccountingPointProperty accountingPointProperty,
                       @NonNull final ServicePartProperty servicePartProperty) {
        return 0;
//        int coefficientNormValue = roomProperties.getCoefficientNormValue();
//
//        long normIndex = item.getNormIndex();
//
//        long durationByDays = roomProperties.getDurationsByDays();
//
//        long daysOfCalculationPeriod = calculationSettings.getDaysOfCalculationPeriod();
//
//        int normValue = servicePartProperty.geyNormValue();
//
//        return (normValue * coefficientNormValue * normIndex * durationByDays / daysOfCalculationPeriod);
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_NORM;
    }
}
