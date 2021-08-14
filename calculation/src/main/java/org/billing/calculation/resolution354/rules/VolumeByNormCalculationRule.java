package org.billing.calculation.resolution354.rules;

import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.model.CalculationMethod;
import org.billing.calculation.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByNormRule")
@Slf4j
public class VolumeByNormCalculationRule implements CalculationRule {

//    @Override
//    public List<CalculationResult> volume(@NonNull final CalculationSettings calculationSettings,
//                                          @NonNull final RoomProperties roomProperties,
//                                          @NonNull final AccountingPointProperty accountingPointProperty,
//                                          @NonNull final ServicePartProperty servicePartProperty) {
//        return Collections.emptyList();
////        int coefficientNormValue = roomProperties.getCoefficientNormValue();
////
////        long normIndex = item.getNormIndex();
////
////        long durationByDays = roomProperties.getDurationsByDays();
////
////        long daysOfCalculationPeriod = calculationSettings.getDaysOfCalculationPeriod();
////
////        int normValue = servicePartProperty.geyNormValue();
////
////        return (normValue * coefficientNormValue * normIndex * durationByDays / daysOfCalculationPeriod);
//    }


    protected CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_NORM;
    }
}
