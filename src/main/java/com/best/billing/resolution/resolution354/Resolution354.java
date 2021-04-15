package com.best.billing.resolution.resolution354;

import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.CalculationSettings;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.resolution.CalculationValidator;
import com.best.billing.resolution.Resolution;
import lombok.NonNull;
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
    public List<CalculationRule> getCalculationRulesForEvent(@NonNull final CalculationSettings calculationSettings,
                                                             @NonNull final RoomProperties roomProperties,
                                                             @NonNull final AccountingPointProperty accountingPointProperty,
                                                             @NonNull final ServicePartProperty servicePartProperty
    ) {
        return rules.entrySet().stream().filter(
                item -> item.getValue().canCalculateVolume(calculationSettings, roomProperties, accountingPointProperty, servicePartProperty))
                .flatMap(item -> List.of(item.getKey()).stream())
                .collect(Collectors.toList());
    }
}
