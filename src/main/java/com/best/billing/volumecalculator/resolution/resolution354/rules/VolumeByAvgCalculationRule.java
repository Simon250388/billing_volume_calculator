package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("VolumeByAvgRule")
public class VolumeByAvgCalculationRule implements CalculationRule {
    @Override
    public long volume(@NonNull CalculationItem item) {
        return item.getAccountingPointServiceAvgVolume().getAvgVolume();
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_AVG_VOLUME;
    }
}
