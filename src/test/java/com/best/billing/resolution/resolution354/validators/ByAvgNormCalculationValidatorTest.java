package com.best.billing.resolution.resolution354.validators;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import com.best.billing.resolution.CalculationValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ByAvgNormCalculationValidatorTest {

    private final CalculationValidator validator = new ByAvgNormCalculationValidator();

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_meter_value_then_canCalculateVolume_return_false() {

        RoomProperties roomProperties = RoomProperties.builder().build();

        AccountingPointProperty accountingPointProperty = AccountingPointProperty
                .builder()
                .serviceActive(true)
                .meterState(MeterState.ACTIVE)
                .build();

        ServicePartProperty servicePartProperty = ServicePartProperty
                .builder()
                .build();

        assertFalse(validator.canCalculateVolume(roomProperties, accountingPointProperty, servicePartProperty));
    }

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_no_meter_value_and_avg_volume_is_null_then_canCalculateVolume_return_true() {

        RoomProperties roomProperties = RoomProperties.builder().build();

        AccountingPointProperty accountingPointProperty = AccountingPointProperty
                .builder()
                .serviceActive(true)
                .meterState(MeterState.ACTIVE)
                .build();

        ServicePartProperty servicePartProperty = ServicePartProperty
                .builder()
                .serviceAvgVolume(ServicePartProperty.AVG_VOLUME_EMPTY_VALUE)
                .build();

        assertTrue(validator.canCalculateVolume(roomProperties, accountingPointProperty, servicePartProperty));
    }

    @Test
    void when_service_is_active_and_meter_state_is_active_and_there_is_no_meter_value_and_avg_volume_is_not_null_then_canCalculateVolume_return_true() {
        RoomProperties roomProperties = RoomProperties.builder().build();

        AccountingPointProperty accountingPointProperty = AccountingPointProperty
                .builder()
                .serviceActive(true)
                .meterState(MeterState.ACTIVE)
                .build();

        ServicePartProperty servicePartProperty = ServicePartProperty
                .builder()
                .serviceAvgVolume(1)
                .build();

        assertFalse(validator.canCalculateVolume(roomProperties, accountingPointProperty, servicePartProperty));
    }
}
