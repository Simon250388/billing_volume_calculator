package com.best.billing.volumecalculator.helpers;

import com.best.billing.volumecalculator.model.ServiceVolumeValue;

import java.util.List;
import java.util.stream.Stream;

public interface Calculator {
    List<ServiceVolumeValue> calculate(Stream<CalculationItem> items);
}
