package com.best.billing.volumecalculator.service;

import com.best.billing.departmen.customer.*;
import com.best.billing.resolution.CalculationRule;
import com.best.billing.resolution.Resolution;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CalculatorImpl implements Calculator {
    @Override
    public List<Map<RoomEvent, List<CalculationResult>>> calculate(
            @NonNull final CalculationSettings calculationSettings,
            @NonNull final Resolution resolution,
            @NonNull final RoomEventsJournal eventsJournal) {

        final List<Map<RoomEvent, List<CalculationResult>>> calculationResult = new ArrayList<>();

        for (RoomEvent event : eventsJournal.getOrderEvents()) {
            List<CalculationResult> volumesForEvent = new ArrayList<>();
            RoomProperties roomProperty = eventsJournal.getRoomProperties(event);
            for (AccountingPointProperty accountingPointProperty : roomProperty.getAccountingPointProperties()) {
                for (ServicePartProperty servicePartProperty : accountingPointProperty.getServicePartProperties()) {
                    List<CalculationRule> rules = resolution.getCalculationRulesForEvent(calculationSettings, roomProperty, accountingPointProperty, servicePartProperty);
                    for (CalculationRule rule : rules) {
                        List<CalculationResult> volumes = rule.volume(calculationSettings, roomProperty, accountingPointProperty, servicePartProperty);
                        volumesForEvent.addAll(volumes);
                    }
                    calculationResult.add(Map.of(event, volumesForEvent));
                }
            }
        }
        return calculationResult;
    }
}
