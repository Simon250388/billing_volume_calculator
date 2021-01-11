package com.best.billing.volumecalculator.resolution.resolution354;

import com.best.billing.volumecalculator.resolution.CalculationRule;
import com.best.billing.volumecalculator.resolution.CalculationValidator;
import com.best.billing.volumecalculator.resolution.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Primary
public class Resolution354 implements Resolution {

    private final Map<CalculationRule, CalculationValidator> rules;

    @Autowired
    public Resolution354(
            @Qualifier("VolumeByMeterValueRule") CalculationRule volumeByMeterValueRule,
            @Qualifier("VolumeByNormRule") CalculationRule volumeByNormRule,
            @Qualifier("VolumeByAvgNormRule") CalculationRule volumeByAvgNormRule,
            @Qualifier("VolumeByAvgRule") CalculationRule volumeByAvgRule,
            @Qualifier("ByMeterVolumeCalculationValidator") CalculationValidator meterVolumeCalculationValidator,
            @Qualifier("ByNormCalculationValidator") CalculationValidator normCalculationValidator,
            @Qualifier("ByAvgNormCalculationValidator") CalculationValidator avgNormCalculationValidator,
            @Qualifier("ByAvgVolumeCalculationValidator") CalculationValidator avgVolumeCalculationValidator) {

        this.rules = Map.of(
                volumeByMeterValueRule, meterVolumeCalculationValidator,
                volumeByNormRule, normCalculationValidator,
                volumeByAvgNormRule, avgNormCalculationValidator,
                volumeByAvgRule, avgVolumeCalculationValidator);
    }

    @Override
    public Map<CalculationRule, CalculationValidator> getRules() {
        return rules;
    }
}
