package com.best.billing.volumecalculator.resolution.resolution354;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class VolumeByMeterValueCalculationRule implements CalculationRule {
    public static final long SECOND_IN_DAY = 86400;

    public Optional<Long> volumeFact(@NonNull CalculationItem item) {

        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.active)) {
            return Optional.empty();
        }

        if (!item.isMeterValueProvide()) {
            return Optional.empty();
        }

        long volume = volumeValueByMeterValue(item);

        if (getDurationBySecondsForPeriodRegistration(item) < SECOND_IN_DAY && volume == 0) {
            return Optional.empty();
        }

        return Optional.of(volume);
    }

    public Optional<Long> volume(@NonNull CalculationItem item) {

        if (!item.getStabPeriod().getAccountingPointMeterState().getMeterState().equals(MeterState.active)) {
            return Optional.empty();
        }

        if (!item.isMeterValueProvide()) {
            return Optional.empty();
        }

        boolean isVolumeByLastYear = false;

        if (item.getSeasonalitySetting() != null) {
            isVolumeByLastYear = item.getSeasonalitySetting().getVolumeByLastYear();
        }

        if (isVolumeByLastYear) {
            return Optional.empty();
        }

        long volume = volumeValueByMeterValue(item);

        if (getDurationBySecondsForPeriodRegistration(item) < SECOND_IN_DAY && volume == 0) {
            return Optional.empty();
        }

        return Optional.of(volume);
    }

    private long volumeValueByMeterValue(@NonNull CalculationItem item) {
        return item.getMeterValuesEnd().get(0).getValue() - item.getMeterValuesStart().get(0).getValue();
    }

    private long getDurationBySecondsForPeriodRegistration(@NonNull CalculationItem item) {

        LocalDate registrationPeriodEnd = item.getEndOfCalculationPeriod().toLocalDate();

        if (item.getStabPeriod().getNextRow() != null) {
            registrationPeriodEnd = item.getStabPeriod().getNextRow().getRegistrationPeriod();
        }

        return ChronoUnit.SECONDS.between(registrationPeriodEnd, item.getStabPeriod().getRegistrationPeriod());
    }
}
