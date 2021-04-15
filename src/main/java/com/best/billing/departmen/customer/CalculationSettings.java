package com.best.billing.departmen.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder()
@AllArgsConstructor
public class CalculationSettings {
    public LocalDate getCalculationPeriod() {
        return null;
    }

    public long getDurationByDaysOfCalculationPeriod() {
        return 1;
    }
}
