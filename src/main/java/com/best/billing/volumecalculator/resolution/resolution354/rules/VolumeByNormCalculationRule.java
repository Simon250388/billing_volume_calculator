package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.commonsettings.model.KeyNormValue;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.helpers.DurationCalculator;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VolumeByNormCalculationRule implements CalculationRule {

    private final DurationCalculator durationCalculator;


    @Autowired
    public VolumeByNormCalculationRule(DurationCalculator durationCalculator) {
        this.durationCalculator = durationCalculator;
    }

    @Override
    public long volume(@NonNull CalculationItem item) {
        int coefficientNormValue = item.getCoefficientNormValue();

        long normIndex = item.getNormIndex();

        long durationByDays = durationCalculator.getDurationByDays(item);

        int normValue = Optional.ofNullable(item.getKeyNormValue()).orElse(new KeyNormValue()).getNormValue();

        return (normValue * coefficientNormValue * normIndex * durationByDays);
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_NORM;
    }
}
