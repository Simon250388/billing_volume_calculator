package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import lombok.NonNull;

public class VolumeByMeterValueCalculationRule implements CalculationRule {

    public long volumeFact(@NonNull CalculationItem item) {
        return volumeValueByMeterValue(item);
    }

    public long volume(@NonNull CalculationItem item) {
        return volumeValueByMeterValue(item);
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_METER;
    }

    private long volumeValueByMeterValue(@NonNull CalculationItem item) {
        return item.getMeterValuesEnd().get(0).getValue() - item.getMeterValuesStart().get(0).getValue();
    }
}
