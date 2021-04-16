package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.*;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Qualifier("VolumeByNormRule")
@Slf4j
public class VolumeByNormCalculationRule implements CalculationRule {

    @Override
    public List<CalculationResult> volume(@NonNull final CalculationSettings calculationSettings,
                                          @NonNull final RoomProperties roomProperties,
                                          @NonNull final AccountingPointProperty accountingPointProperty,
                                          @NonNull final ServicePartProperty servicePartProperty) {
        return Collections.emptyList();
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


    protected CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_NORM;
    }
}
