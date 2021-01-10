package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.volumecalculator.helpers.DurationCalculator;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("VolumeByAvgNormRule")
public class VolumeByAvgNormCalculationRule extends VolumeByNormCalculationRule implements CalculationRule {

    @Autowired
    public VolumeByAvgNormCalculationRule(DurationCalculator durationCalculator) {
        super(durationCalculator);
    }

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_AVG_NORM;
    }
}
