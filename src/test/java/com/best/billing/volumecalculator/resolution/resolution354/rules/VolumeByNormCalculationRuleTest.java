package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.commonsettings.model.KeyNormValue;
import com.best.billing.commonsettings.model.SeasonalitySetting;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.helpers.DurationCalculator;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class VolumeByNormCalculationRuleTest {

    private final DurationCalculator durationCalculator = Mockito.mock(DurationCalculator.class);
    private final CalculationRule calculationRule = new VolumeByNormCalculationRule(durationCalculator);

    @Test
    void when_then_return_0() {
        final long DURATION_BY_DAYS = 30;
        final long COUNT_DAYS_OF_MONTH = 30;
        final int CoefficientNormValue = 1;
        final long NORM_INDEX = 5;
        final KeyNormValue keyNormValue = KeyNormValue.builder()
                .normValue(15)
                .build();

        CalculationItem calculationItem = Mockito.mock(CalculationItem.class);

        Mockito.when(calculationItem.getCoefficientNormValue()).thenReturn(CoefficientNormValue);

        Mockito.when(calculationItem.getNormIndex()).thenReturn(NORM_INDEX);

        Mockito.when(calculationItem.getKeyNormValue()).thenReturn(keyNormValue);

        Mockito.when(durationCalculator.getDurationByDays(any())).thenReturn(DURATION_BY_DAYS);
        assertEquals(0, calculationRule.volume(calculationItem));
    }
}