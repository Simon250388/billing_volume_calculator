package com.best.billing.departmen.customer;

import com.best.billing.metervalues.model.MeterValue;
import com.best.billing.metervalues.model.MethodProvideMeterValue;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomEvent {
    LocalDateTime getPeriodFact();
    LocalDateTime getPeriod();
    RoomProperties register(RoomProperties previousProperties, RoomEvent previousEvent, List<MeterValue> currentMeterValues);
    boolean isProvideMeterValue();
    MethodProvideMeterValue getMethodProvideMeterValue();
    long getId();
}
