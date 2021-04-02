package com.best.billing.resolution.resolution354;

import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.resolution.CalculationValidator;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.resolution.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<CalculationRule> getCalculationRulesForEvent(AccountingPointProperties accountingPointProperties) {
        return rules.entrySet().stream().filter(
                item -> item.getValue().canCalculateVolume(accountingPointProperties))
                .flatMap(item -> List.of(item.getKey()).stream())
                .collect(Collectors.toList());
    }
}
