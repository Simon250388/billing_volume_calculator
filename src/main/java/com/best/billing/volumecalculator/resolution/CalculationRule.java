package com.best.billing.volumecalculator.resolution;

import com.best.billing.volumecalculator.helpers.CalculationItem;
import lombok.NonNull;

public interface CalculationRule {
    long volumeFact(@NonNull CalculationItem item);
    long volume(@NonNull CalculationItem item);
}
