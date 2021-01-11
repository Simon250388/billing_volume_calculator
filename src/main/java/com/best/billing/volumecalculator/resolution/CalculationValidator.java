package com.best.billing.volumecalculator.resolution;

import com.best.billing.volumecalculator.helpers.CalculationItem;

public interface CalculationValidator {
    boolean canCalculateVolume(CalculationItem item);
}
