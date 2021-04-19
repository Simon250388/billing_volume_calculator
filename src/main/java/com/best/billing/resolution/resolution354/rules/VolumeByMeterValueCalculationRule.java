package com.best.billing.resolution.resolution354.rules;

import com.best.billing.departmen.customer.*;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.volumecalculator.model.CalculationMethod;
import com.best.billing.volumecalculator.service.CalculationResultImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component()
@Qualifier("VolumeByMeterValueRule")
@Slf4j
public class VolumeByMeterValueCalculationRule implements CalculationRule {

    public List<CalculationResult> volume(
            @NonNull final CalculationSettings calculationSettings,
            @NonNull final RoomProperties roomProperties,
            @NonNull final AccountingPointProperty accountingPointProperty,
            @NonNull final ServicePartProperty servicePartProperty) {
        return volumeValueByMeterValue(roomProperties, accountingPointProperty, servicePartProperty);
    }


    private CalculationMethod getCalculationMethod() {
        return CalculationMethod.BY_METER;
    }

    private List<CalculationResult> volumeValueByMeterValue(@NonNull final RoomProperties roomProperties,
                                           @NonNull final AccountingPointProperty accountingPointProperty,
                                           @NonNull final ServicePartProperty servicePartProperty) {

        List<CalculationResult> result = new ArrayList<>();

        List<RoomMeterValue> currentMeterValuesByMeterId = roomProperties.getRoomMeterValues().stream()
                .filter(roomMeterValue -> roomMeterValue.getMeterId() == accountingPointProperty.getMeterId())
                .collect(Collectors.toList());

        List<RoomMeterValue> previousMeterValuesByMeterId = roomProperties.getPreviousRoomMeterValues().stream()
                .filter(roomMeterValue -> roomMeterValue.getMeterId() == accountingPointProperty.getMeterId())
                .collect(Collectors.toList());

        for (RoomMeterValue currentMeterValue : currentMeterValuesByMeterId) {
            double previousValue = previousMeterValuesByMeterId.stream()
                    .filter(roomMeterValue -> roomMeterValue.getScaleId() == currentMeterValue.getScaleId()
                            && roomMeterValue.getReitGroupId() == currentMeterValue.getReitGroupId())
                    .findAny().orElse(currentMeterValue).getValue();

            double volumeByScale = currentMeterValue.getValue() - previousValue;

            result.add(CalculationResultImpl.builder()
                    .calculationMethod(getCalculationMethod())
                    .reitGroupId(currentMeterValue.getReitGroupId())
                    .scaleId(currentMeterValue.getScaleId())
                    .volume(volumeByScale)
                    .build());
        }
        return result;
    }
}
