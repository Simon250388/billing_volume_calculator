package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.stabs.CalculationItemStabFactory;
import com.best.billing.stabs.StabFactory;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.model.StabPeriod;
import com.best.billing.volumecalculator.resolution.resolution354.CalculationValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ByMeterVolumeCalculationValidatorTest {

    private final CalculationValidator validator = new ByMeterVolumeCalculationValidator();
    private final StabFactory stabFactory = StabFactory.builder().build();

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_meter_value_then_canCalculateVolume_return_true() {
        CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory, true, MeterState.ACTIVE);
        CalculationItem spy = Mockito.spy(calculationItem);
        Mockito.when(spy.meterValuesIsProvide()).thenReturn(true);
        assertTrue(validator.canCalculateVolume(spy));
    }

    @Test
    void when_service_is_active_and_meter_state_is_disable_then_canCalculateVolume_return_false() {
        CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory,true, MeterState.DISABLE);
        assertFalse(validator.canCalculateVolume(calculationItem));
    }

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_meter_value_then_isCanCalculateVolumeFact_return_true() {
        CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory,true, MeterState.ACTIVE);
        CalculationItem spy = Mockito.spy(calculationItem);
        Mockito.when(spy.meterValuesIsProvide()).thenReturn(true);
        assertTrue(validator.isCanCalculateVolumeFact(spy));
    }

    @Test
    void when_service_is_active_and_meter_state_is_disable_then_isCanCalculateVolumeFact_return_false() {
        CalculationItem calculationItem = CalculationItemStabFactory.getCalculationItem(stabFactory,true, MeterState.DISABLE);
        assertFalse(validator.isCanCalculateVolumeFact(calculationItem));
    }


}
