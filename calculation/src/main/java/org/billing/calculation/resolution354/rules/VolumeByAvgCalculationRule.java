package org.billing.calculation.resolution354.rules;

import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.model.CalculationMethod;
import org.billing.calculation.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByAvgRule")
@Slf4j
public class VolumeByAvgCalculationRule implements CalculationRule {
//    @Override
//    public List<CalculationResult> volume(
//            @NonNull final CalculationSettings calculationSettings,
//            @NonNull final RoomProperties roomProperties,
//            @NonNull final AccountingPointProperty accountingPointProperty,
//            @NonNull final ServicePartProperty servicePartProperty) {
//
//        double volume = servicePartProperty.getServiceAvgVolume() * roomProperties.getDurationsByDays() / calculationSettings.getDurationByDaysOfCalculationPeriod();
//
//        return Collections.singletonList(
//                CalculationResultImpl.builder()
//                        .calculationMethod(getCalculationMethod())
//                        .volume(volume)
//                        .build()
//        );
//    }


    private CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_AVG_VOLUME;
    }
}
