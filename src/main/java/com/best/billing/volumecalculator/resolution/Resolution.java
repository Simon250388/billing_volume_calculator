package com.best.billing.volumecalculator.resolution;

import java.util.Map;

public interface Resolution {
    Map<CalculationRule, CalculationValidator> getRules();
}
