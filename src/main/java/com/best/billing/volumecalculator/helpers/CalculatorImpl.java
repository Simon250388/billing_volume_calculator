package com.best.billing.volumecalculator.helpers;

import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.model.ServiceVolumeValue;
import com.best.billing.volumecalculator.resolution.Resolution;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
@Slf4j
public class CalculatorImpl implements Calculator {

    private final CalculationItemBuilder calculationItemBuilder;

    @Autowired
    public CalculatorImpl(CalculationItemBuilder calculationItemBuilder) {
        this.calculationItemBuilder = calculationItemBuilder;
    }

    @Override
    public List<ServiceVolumeValue> calculate(@NonNull LocalDate calculationPeriod, @NonNull Resolution resolution) {
        return calculationItemBuilder.buildStream(calculationPeriod)
                .flatMap(item -> {
                            List<ServiceVolumeValue> serviceVolumeValues = new ArrayList<>();
                            resolution.getRules().forEach((rule, validator) -> {
                                if (validator.canCalculateVolume(item)) {
                                    buildVolumeValue(item, rule.volume(item));
                                }
                                if (validator.isCanCalculateVolumeFact(item)) {
                                    buildVolumeValue(item, rule.volumeFact(item));
                                }
                            });
                            return serviceVolumeValues.stream();
                        }
                )
                .collect(Collectors.toList());
    }

    private ServiceVolumeValue buildVolumeValue(@NonNull CalculationItem item, long volume) {
        return ServiceVolumeValue.builder()
                .calculationMethod(CalculationMethod.METHOD_BY_METER)
                .stabPeriod(item.getStabPeriod())
                .volume(volume)
                .factVolume(0)
                .build();
    }
}
