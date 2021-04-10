package com.best.billing.departmen.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.*;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class RoomProperties {
    long roomOwner;
    long roomPrescribed;
    long roomResident;
    long roomCommonSquare;
    LocalDateTime registrationPeriod;
    LocalDateTime registrationPeriodFact;
    List<AccountingPointProperty> accountingPointProperties;

    public RoomPropertiesBuilder getNewInstance(@NonNull final LocalDateTime registrationPeriod, @NonNull final LocalDateTime registrationPeriodFact) {

        List<AccountingPointProperty> newAccountingPointProperties = new ArrayList<>();

        for (AccountingPointProperty accountingPointProperty : this.accountingPointProperties) {
            newAccountingPointProperties.add(accountingPointProperty.getNewInstance());
        }
        return this.toBuilder()
                .accountingPointProperties(newAccountingPointProperties)
                .registrationPeriod(registrationPeriod)
                .registrationPeriodFact(registrationPeriodFact);

    }

    public boolean isMeterValuesProvideForAllPointsOfServices(long servicePartId) {
        return false;
    }

    public boolean isHasAvgVolumeForAllPointsOfServices(long servicePartId) {
        return false;
    }
}
