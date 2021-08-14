package org.billing.calculation.resolution354.validators;

import lombok.extern.slf4j.Slf4j;
import org.billing.calculation.resolution.CalculationValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ByAvgVolumeCalculationValidator")
@Slf4j
public class ByAvgVolumeCalculationValidator implements CalculationValidator {
//    @Override
//    public boolean canCalculateVolume(@NonNull final CalculationSettings calculationSettings,
//                                      @NonNull final RoomProperties roomProperties,
//                                      @NonNull final AccountingPointProperty accountingPointProperty,
//                                      @NonNull final ServicePartProperty servicePartProperty) {
//        return accountingPointProperty.isServiceActive() &&
//                accountingPointProperty.getMeterState() == MeterState.ACTIVE
//                && roomProperties.isMeterValuesProvideForAllPointsOfServices(servicePartProperty.getServicePartId())
//                && roomProperties.isHasAvgVolumeForAllPointsOfServices(servicePartProperty.getServicePartId());
//    }
}
