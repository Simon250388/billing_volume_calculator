package com.best.billing.stabs;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.volumecalculator.helpers.CalculationItem;
import com.best.billing.volumecalculator.model.StabPeriod;
import lombok.Builder;

@Builder
public class CalculationItemStabFactory {

    public static CalculationItem getCalculationItem(StabFactory stabFactory, boolean serviceIsActive, MeterState meterState) {

        StabPeriod stabPeriod = StabPeriod
                .builder()
                .accountingPointKeyRoomServiceEntity(stabFactory.accountingPointKeyRoomServiceEntity)
                .accountingPointMeterState(stabFactory.AccountingPointMeterState(meterState))
                .accountingPointServiceState(stabFactory.AccountingPointServiceState(serviceIsActive))
                .build();

        return CalculationItem.builder()
                .stabPeriod(stabPeriod)
                .build();
    }
}
