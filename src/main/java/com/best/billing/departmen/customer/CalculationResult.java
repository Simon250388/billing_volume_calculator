package com.best.billing.departmen.customer;

import com.best.billing.volumecalculator.model.CalculationMethod;

public interface CalculationResult {
    CalculationMethod getCalculationMethod();
    double getVolume();
    double getVolumeFact();
    long getScaleId();
    long getReitGroupId();
}
