package com.best.billing.volumecalculator.helpers;

import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class DurationCalculatorImpl implements DurationCalculator {
    @Override
    public long getDurationByDays(CalculationItem item) {
        return ChronoUnit.DAYS.between(item.getStabPeriod().getRegistrationPeriod(), item.getCalculationPeriodEnd());
    }

    @Override
    public long daysOfCalculationPeriod(CalculationItem item) {
        return ChronoUnit.DAYS.between(item.getCalculationPeriodStart(), item.getCalculationPeriodEnd());
    }

    @Override
    public long getDurationBySeconds(CalculationItem item) {
        return 0;
    }
}
