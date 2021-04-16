package com.best.billing.resolution.resolution354.rules;

import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("VolumeByAvgNormRule")
@Slf4j
public class VolumeByAvgNormCalculationRule extends VolumeByNormCalculationRule implements CalculationRule {

    @Override
    protected CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_AVG_NORM;
    }
}
