package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("VolumeByMeterValueRule")
public class VolumeByMeterValueCalculationRule implements CalculationRule {

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
