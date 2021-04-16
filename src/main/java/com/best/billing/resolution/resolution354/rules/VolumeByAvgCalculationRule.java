package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.*;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.service.CalculationResultImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Qualifier("VolumeByAvgRule")
@Slf4j
public class VolumeByAvgCalculationRule implements CalculationRule {
    @Override
    public List<CalculationResult> volume(
            @NonNull final CalculationSettings calculationSettings,
            @NonNull final RoomProperties roomProperties,
            @NonNull final AccountingPointProperty accountingPointProperty,
            @NonNull final ServicePartProperty servicePartProperty) {

        double volume = servicePartProperty.getServiceAvgVolume() * roomProperties.getDurationsByDays() / calculationSettings.getDurationByDaysOfCalculationPeriod();

        return Collections.singletonList(
                CalculationResultImpl.builder()
                        .calculationMethod(getCalculationMethod())
                        .volume(volume)
                        .build()
        );
    }


    private CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_AVG_VOLUME;
    }
}
