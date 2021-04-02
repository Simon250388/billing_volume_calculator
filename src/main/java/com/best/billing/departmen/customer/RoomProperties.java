package com.best.billing.departmen.customer;

import com.best.billing.common.model.AccountingPoint;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Value
@Builder(toBuilder = true)
public class RoomProperties {
    long roomOwner;
    long roomPrescribed;
    long roomResident;
    long roomCommonSquare;
    LocalDateTime registrationPeriod;
    LocalDateTime registrationPeriodFact;
    Map<AccountingPoint, AccountingPointProperties> accountingPointProperties;

    public RoomPropertiesBuilder getCloneBuilder(@NonNull final LocalDateTime registrationPeriod, @NonNull final LocalDateTime registrationPeriodFact) {
        Map<AccountingPoint, AccountingPointProperties> newInstanceAccountingPointProperties = new HashMap<>();
        this.accountingPointProperties.forEach((key, value) ->
                newInstanceAccountingPointProperties.put(key, value.toBuilder()
                        .roomOwner(this.roomOwner)
                        .roomResident(this.roomResident)
                        .roomPrescribed(this.roomPrescribed)
                        .build()));

        return this.toBuilder()
                .registrationPeriod(registrationPeriod)
                .registrationPeriodFact(registrationPeriodFact)
                .accountingPointProperties(newInstanceAccountingPointProperties);
    }
}
