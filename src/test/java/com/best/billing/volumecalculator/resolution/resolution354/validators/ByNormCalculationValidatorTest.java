package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.stabs.CalculationItemStabFactory;
import com.best.billing.stabs.StabFactory;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.resolution.resolution354.CalculationValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ByNormCalculationValidatorTest {
    private final CalculationValidator validator = new ByNormCalculationValidator();
    private final StabFactory stabFactory = StabFactory.builder().build();

    @Test
    void when_service_is_active_and_meter_state_is_null_then_canCalculateVolume_true() {
        CalculationItem item = CalculationItemStabFactory.getCalculationItem(stabFactory,true, null);
        assertTrue(validator.canCalculateVolume(item));
    }

    @Test
    void when_service_is_active_and_meter_state_is_active_then_canCalculateVolume_false() {
        CalculationItem item = CalculationItemStabFactory.getCalculationItem(stabFactory, true, MeterState.ACTIVE);
        assertFalse(validator.canCalculateVolume(item));
    }

    @Test
    void when_service_is_active_and_meter_state_is_not_active_then_canCalculateVolume_false() {
        CalculationItem item = CalculationItemStabFactory.getCalculationItem(stabFactory, true, MeterState.DISABLE);
        assertTrue(validator.canCalculateVolume(item));
    }

    @Test
    void when_service_is_not_active_and_meter_state_is_null_then_canCalculateVolume_false() {
        CalculationItem item = CalculationItemStabFactory.getCalculationItem(stabFactory, false, null);
        assertFalse(validator.canCalculateVolume(item));
    }
}
