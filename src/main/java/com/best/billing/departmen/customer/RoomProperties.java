package com.best.billing.departmen.customer;

import com.best.billing.metervalues.model.MeterValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    List<RoomMeterValue> roomMeterValues;

    public RoomPropertiesBuilder cloneBuilder(final RoomEvent previousEvent, @NonNull final List<MeterValue> currentMeterValues) {
        LocalDateTime previousRegistrationPeriod;
        LocalDateTime previousRegistrationPeriodFact;

        List<AccountingPointProperty> newAccountingPointProperties = new ArrayList<>();

        for (AccountingPointProperty accountingPointProperty : this.accountingPointProperties) {
            newAccountingPointProperties.add(accountingPointProperty.getNewInstance());
        }

        List<RoomMeterValue> newMeterValues = new ArrayList<>();

        for (MeterValue meterValue : currentMeterValues) {
            newMeterValues.add(
                    RoomMeterValue.builder()
                            .meterId(meterValue.getMeter().getId())
                            .value(meterValue.getValue())
                            // TODO FIX ME
                            .scaleId(-1)
                            // TODO FIX ME
                            .rateZoneId(-1)
                            .build()
            );
        }

        if (previousEvent == null) {
            previousRegistrationPeriod = LocalDateTime.of(1, 1, 1, 0, 0, 0);
            previousRegistrationPeriodFact = LocalDateTime.of(1, 1, 1, 0, 0, 0);
        } else {
            previousRegistrationPeriod = previousEvent.getPeriod();
            previousRegistrationPeriodFact = previousEvent.getPeriodFact();
        }

        return this.toBuilder()
                .accountingPointProperties(newAccountingPointProperties)
                .roomMeterValues(newMeterValues)
                .registrationPeriod(null)
                .registrationPeriodFact(null)
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

    public List<RoomMeterValue> getPreviousRoomMeterValues() {
        return Collections.emptyList();
    }
}
