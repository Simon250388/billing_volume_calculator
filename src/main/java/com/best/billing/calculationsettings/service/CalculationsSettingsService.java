package com.best.billing.calculationsettings.service;

import com.best.billing.departmen.customer.CalculationSettings;

import java.time.LocalDate;

public interface CalculationsSettingsService {
    CalculationSettings getCalculationSettings(LocalDate calculationPeriod);

}
