package com.best.billing.volumecalculator.resolution.resolution354;

import com.best.billing.volumecalculator.helpers.CalculationItem;

public interface CalculationValidator {
    boolean canCalculateVolume(CalculationItem item);
    boolean isCanCalculateVolumeFact(CalculationItem item);
}
