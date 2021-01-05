package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.helpers.DurationCalculator;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

public class VolumeByNormCalculationRule implements CalculationRule {

    @Autowired
    private DurationCalculator durationCalculator;

    @Override
    public long volumeFact(@NonNull CalculationItem item) {
        return volumeValueByNormValue(item);
    }

    @Override
    public long volume(@NonNull CalculationItem item) {
        return volumeValueByNormValue(item);
    }

    private long volumeValueByNormValue(@NonNull CalculationItem item) {

        if (item.getSeasonalitySetting() == null || item.getSeasonalitySetting().getDoNotUseSeasonality() == null ) {
            return 0;
        }

        int coefficientNormValue = item.getSeasonalitySetting().getDoNotUseSeasonality()
                ? item.getSeasonalitySetting().getCoefficientNormValueDoNotUseSeasonality() : item.getSeasonalitySetting().getCoefficientNormValue();

        int roomNormIndex = roomNormIndex(item);

        double durationByDays = durationCalculator.getDurationByDays(item);

        if (item.getKeyNormValue() == null) {
            return 0;
        }

        return (long) (item.getKeyNormValue().getNormValue() * coefficientNormValue * roomNormIndex * durationByDays);
    }

    private int roomNormIndex(@NonNull CalculationItem item) {
        if (item.getCalculationMethodByDirectionOfUse() == null) {
            // TODO add log info
            return 0;
        }

        if (item.getCalculationMethodByDirectionOfUse().getSquareType() != null) {
            return item.getStabPeriod().getRoomSquare().getValue();
        } else if (item.getStabPeriod().getRoomResident() != null && item.getStabPeriod().getRoomResident().getResidentCount() != 0) {
            return item.getStabPeriod().getRoomResident().getResidentCount();
        } else if (item.getStabPeriod().getRoomPrescribed() != null && item.getStabPeriod().getRoomPrescribed().getPrescribedCount() != 0) {
            return item.getStabPeriod().getRoomPrescribed().getPrescribedCount();
        } else if (item.getStabPeriod().getRoomOwner() != null) {
            return item.getStabPeriod().getRoomOwner().getOwnerCount();
        } else {
            return 0;
            // TODO
            // add log info
        }
    }
}
