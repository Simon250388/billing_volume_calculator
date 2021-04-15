package com.best.billing.departmen.customer;

import com.best.billing.calculationsettings.service.CalculationsSettingsService;
import com.best.billing.resolution.Resolution;
import com.best.billing.volumecalculator.service.Calculator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperatorImpl implements Operator {

    private final Resolution resolution;
    private final Calculator calculator;
    private final RoomEventService eventService;
    private final CalculationsSettingsService calculationsSettingsService;

    @Autowired
    public OperatorImpl(Resolution resolution, Calculator calculator, RoomEventService eventService, CalculationsSettingsService calculationsSettingsService) {
        this.resolution = resolution;
        this.calculator = calculator;
        this.eventService = eventService;
        this.calculationsSettingsService = calculationsSettingsService;
    }

    @Override
    public void getCalculations(@NonNull final LocalDate calculationPeriod, @NonNull final Long keyRoomId) {
        CalculationSettings calculationSettings = this.calculationsSettingsService.getCalculationSettings(calculationPeriod);
        RoomEventsJournal eventJournal = this.eventService.getEventJournal(calculationPeriod, keyRoomId);
        calculator.calculate(calculationSettings, resolution, eventJournal);
    }

    @Autowired
    public void getCalculations(@NonNull final LocalDate calculationPeriod) {
        CalculationSettings calculationSettings = this.calculationsSettingsService.getCalculationSettings(calculationPeriod);
        List<RoomEventsJournal> eventJournals = this.eventService.getEventJournal(calculationSettings.getCalculationPeriod());
        eventJournals.forEach(eventJournal -> calculator.calculate(calculationSettings, resolution, eventJournal));
    }
}
