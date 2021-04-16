package com.best.billing.volumecalculator.service;

import com.best.billing.departmen.customer.CalculationResult;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CalculationResultImpl implements CalculationResult {
    CalculationMethod calculationMethod;
    double volume;
    double volumeFact;
    long scaleId;
    long reitGroupId;
}
