package com.best.billing.volumecalculator.resolution.resolution354.rules;

import com.best.billing.servicebuilder.models.historychange.MeterValue;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.CalculationRule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class VolumeByMeterValueCalculationRuleTest {

    private final CalculationRule rule = new VolumeByMeterValueCalculationRule();

    @Test
    void when_meter_value_start_is_5_end_value_end_15_than_volume_return_10() {
        final long VALUE_START = 5;
        final long VALUE_END = 15;
        final long VALUE_RESULT = 10;

        CalculationItem calculationItem = CalculationItem.builder().build();
        CalculationItem spy = Mockito.spy(calculationItem);
        List<MeterValue> valuesStart = Arrays.asList(
                MeterValue.builder()
                        .value(VALUE_START)
                        .build()
        );

        List<MeterValue> valuesEnd = Arrays.asList(
                MeterValue.builder()
                        .value(VALUE_END)
                        .build()
        );

        when(spy.getMeterValuesStart()).thenReturn(valuesStart);
        when(spy.getMeterValuesEnd()).thenReturn(valuesEnd);

        assertEquals(VALUE_RESULT,rule.volume(spy));
    }
}