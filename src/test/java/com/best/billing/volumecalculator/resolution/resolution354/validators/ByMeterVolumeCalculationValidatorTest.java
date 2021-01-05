package com.best.billing.volumecalculator.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.stabs.StabFactory;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.model.StabPeriod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ByMeterVolumeCalculationValidatorTest {

    private final ByMeterVolumeCalculationValidator validator = new ByMeterVolumeCalculationValidator();

    @Test
    void when_service_is_active_and_meter_state_is_active_then_canCalculateVolume_return_true() {
        CalculationItem calculationItem = getCalculationItem(true, MeterState.ACTIVE);
        assertTrue(validator.canCalculateVolume(calculationItem));
    }

    @Test
    void when_service_is_active_and_meter_state_is_disable_then_canCalculateVolume_return_false() {
        CalculationItem calculationItem = getCalculationItem(true, MeterState.DISABLE);
        assertFalse(validator.canCalculateVolume(calculationItem));
    }

    @Test
    void when_service_is_active_and_meter_state_is_active_then_isCanCalculateVolumeFact_return_true() {
        CalculationItem calculationItem = getCalculationItem(true, MeterState.ACTIVE);
        assertTrue(validator.isCanCalculateVolumeFact(calculationItem));
    }

    @Test
    void when_service_is_active_and_meter_state_is_disable_then_isCanCalculateVolumeFact_return_false() {
        CalculationItem calculationItem = getCalculationItem(true, MeterState.DISABLE);
        assertFalse(validator.isCanCalculateVolumeFact(calculationItem));
    }

    private CalculationItem getCalculationItem(boolean serviceIsActive,MeterState meterState) {
        StabFactory stabFactory = StabFactory.builder().build();

        StabPeriod stabPeriod = StabPeriod
                .builder()
                .accountingPointKeyRoomServiceEntity(stabFactory.accountingPointKeyRoomServiceEntity)
                .accountingPointMeterState(stabFactory.AccountingPointMeterState(meterState))
                .accountingPointServiceState(stabFactory.AccountingPointServiceState(serviceIsActive))
                .build();

        return CalculationItem.builder().stabPeriod(stabPeriod).build();
    }
}
