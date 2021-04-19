package com.best.billing.departmen.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class RoomMeterValue {
    long meterId;
    long reitGroupId;
    long scaleId;
    double value;


}
