package com.best.billing.volumecalculator.resolution;

import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.model.CalculationMethod;
import lombok.NonNull;

public interface CalculationRule {
    long volume(@NonNull CalculationItem item);
    CalculationMethod getCalculationMethod();
}
