package com.best.billing.departmen.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class RoomProperties {
    short roomOwner;
    short roomPrescribed;
    short roomResident;
    int roomCommonSquare;
    LocalDateTime registrationPeriod;
    LocalDateTime registrationPeriodFact;

    LocalDateTime previousEventRegistrationPeriod;
    LocalDateTime previousEventRegistrationPeriodFact;
    List<AccountingPointProperty> accountingPointProperties;


    public RoomPropertiesBuilder getNewInstance(@NonNull final RoomEvent event, final RoomEvent previousEvent) {
        LocalDateTime previousRegistrationPeriod, previousRegistrationPeriodFact;

        List<AccountingPointProperty> newAccountingPointProperties = new ArrayList<>();

        for (AccountingPointProperty accountingPointProperty : this.accountingPointProperties) {
            newAccountingPointProperties.add(accountingPointProperty.getNewInstance());
        }

        if (previousEvent == null) {
            previousRegistrationPeriod =  LocalDateTime.of(1,1,1,0,0,0);
            previousRegistrationPeriodFact =  LocalDateTime.of(1,1,1,0,0,0);
        } else {
            previousRegistrationPeriod = previousEvent.getPeriod();
            previousRegistrationPeriodFact = previousEvent.getPeriod();
        }

        return this.toBuilder()
                .accountingPointProperties(newAccountingPointProperties)
                .registrationPeriod(event.getPeriod())
                .registrationPeriodFact(event.getPeriodFact())
                .previousEventRegistrationPeriod(previousRegistrationPeriod)
                .previousEventRegistrationPeriodFact(previousRegistrationPeriodFact);
    }

    public boolean isMeterValuesProvideForAllPointsOfServices(long servicePartId) {
        return false;
    }

    public boolean isHasAvgVolumeForAllPointsOfServices(long servicePartId) {
        return false;
    }

    public long getDurationsByDays() {
       return registrationPeriod.until(previousEventRegistrationPeriod, ChronoUnit.DAYS);
    }
}
