package com.best.billing.volumecalculator.helpers;

public interface DurationCalculator {
    long getDurationByDays(CalculationItem item);
    long getDurationBySeconds(CalculationItem item);
}
