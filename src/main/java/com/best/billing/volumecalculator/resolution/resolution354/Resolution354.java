package com.best.billing.volumecalculator.resolution.resolution354;

import com.best.billing.volumecalculator.resolution.CalculationRule;
import com.best.billing.volumecalculator.resolution.Resolution;

import java.util.Arrays;
import java.util.List;

public class Resolution354 implements Resolution {
    @Override
    public List<CalculationRule> getRules() {
        return Arrays.asList(
                new VolumeByMeterValueCalculationRule(),
                new VolumeByNormCalculationRule()
        );
    }
}
