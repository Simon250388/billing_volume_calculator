package com.best.billing.departmen.customer;

import com.best.billing.common.model.DifferentiationType;
import com.best.billing.common.model.Provider;
import com.best.billing.common.model.Service;
import com.best.billing.common.model.enums.MeterState;
import com.best.billing.common.model.Meter;
import com.best.billing.common.model.enums.SquareType;
import com.best.billing.roomevents.models.RoomRateGroup;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class AccountingPointProperties {
    Service service;
    SquareType serviceSquareType;
    RoomRateGroup roomRateGroup;
    Provider provider;
    boolean serviceActive;
    Meter meter;
    MeterState meterState;
    DifferentiationType differentiationType;
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
