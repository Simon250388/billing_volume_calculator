package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.stabs.AccountingPointServiceAvgVolumeStabBuilder;
import com.best.billing.stabs.CalculationItemStabFactory;
import com.best.billing.stabs.StabFactory;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.resolution354.CalculationValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ByAvgNormCalculationValidatorTest {

    private final CalculationValidator validator = new ByAvgNormCalculationValidator();
    private final StabFactory stabFactory = StabFactory.builder().build();

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_meter_value_then_canCalculateVolume_return_false() {
        CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory,true, MeterState.ACTIVE);
        CalculationItem spy = Mockito.spy(calculationItem);
        Mockito.when(spy.meterValuesIsProvide()).thenReturn(true);
        assertFalse(validator.canCalculateVolume(spy));
    }

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_no_meter_value_and_avg_volume_is_null_then_canCalculateVolume_return_true() {
        CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory,true, MeterState.ACTIVE);
        CalculationItem spy = Mockito.spy(calculationItem);
        Mockito.when(spy.meterValuesIsProvide()).thenReturn(false);
        Mockito.when(spy.getAccountingPointServiceAvgVolume()).thenReturn(null);
        assertTrue(validator.canCalculateVolume(spy));
    }

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_no_meter_value_and_avg_volume_is_not_null_then_canCalculateVolume_return_true() {
        CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory,true, MeterState.ACTIVE);
        CalculationItem spy = Mockito.spy(calculationItem);
        Mockito.when(spy.meterValuesIsProvide()).thenReturn(false);
        Mockito.when(spy.getAccountingPointServiceAvgVolume())
                .thenReturn(AccountingPointServiceAvgVolumeStabBuilder.accountingPointServiceAvgVolume(stabFactory,30L));
        assertFalse(validator.canCalculateVolume(spy));
    }
}
