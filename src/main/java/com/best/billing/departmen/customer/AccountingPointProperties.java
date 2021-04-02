package com.best.billing.departmen.customer;

import com.best.billing.common.model.enums.MeterState;
import com.best.billing.common.model.enums.SquareType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class AccountingPointProperties {
    long serviceId;
    SquareType serviceSquareType;
    long roomRateGroupId;
    long providerId;
    boolean serviceActive;
    long meterId;
    MeterState meterState;
    long differentiationTypeId;
    long roomOwner;
    long roomPrescribed;
    long roomResident;
    long roomCommonSquare;
    LocalDateTime registrationPeriod;
    LocalDateTime registrationPeriodFact;
    boolean meterValuesProvide;
    long serviceAvgVolume;

    public boolean isHasServiceAvgVolume() {
        return serviceAvgVolume != -1;
    }
}
