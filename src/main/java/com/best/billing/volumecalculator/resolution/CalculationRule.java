package com.best.billing.volumecalculator.resolution;

import com.best.billing.volumecalculator.helpers.CalculationItem;
import lombok.NonNull;

import java.util.Optional;

public interface CalculationRule {
    Optional<Long> volumeFact(@NonNull CalculationItem item);
    Optional<Long> volume(@NonNull CalculationItem item);
}
