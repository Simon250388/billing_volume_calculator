package com.best.billing.volumecalculator.helpers;

import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.model.ServiceVolumeValue;
import com.best.billing.volumecalculator.resolution.Resolution;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
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
                                    serviceVolumeValues.add(
                                            buildVolumeValue(item, rule.volume(item), rule.getCalculationMethod()));
                                }
                            });
                            return serviceVolumeValues.stream();
                        }
                )
                .collect(Collectors.toList());
    }

    private ServiceVolumeValue buildVolumeValue(@NonNull CalculationItem item, long volume, CalculationMethod calculationMethod) {
        return ServiceVolumeValue.builder()
                .calculationMethod(calculationMethod)
                .stabPeriod(item.getStabPeriod())
                .volume(volume)
                .build();
    }
}
