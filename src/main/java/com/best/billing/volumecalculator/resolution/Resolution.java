package com.best.billing.volumecalculator.resolution;

import com.best.billing.volumecalculator.resolution.resolution354.CalculationValidator;

import java.util.Map;

public interface Resolution {
    Map<CalculationRule, CalculationValidator> getRules();
}
