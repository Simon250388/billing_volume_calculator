package com.best.billing.volumecalculator.resolution.resolution354;

import com.best.billing.volumecalculator.resolution.CalculationRule;
import com.best.billing.volumecalculator.resolution.Resolution;
import com.best.billing.volumecalculator.resolution.resolution354.rules.VolumeByMeterValueCalculationRule;
import com.best.billing.volumecalculator.resolution.resolution354.rules.VolumeByNormCalculationRule;
import com.best.billing.volumecalculator.resolution.resolution354.validators.ByMeterVolumeCalculationValidator;
import com.best.billing.volumecalculator.resolution.resolution354.validators.ByNormCalculationValidator;

import java.util.Map;

public class Resolution354 implements Resolution {
    @Override
    public Map<CalculationRule, CalculationValidator> getRules() {
        return Map.of(new VolumeByMeterValueCalculationRule(), new ByMeterVolumeCalculationValidator(),
                new VolumeByNormCalculationRule(), new ByNormCalculationValidator()
        );
    }
}
