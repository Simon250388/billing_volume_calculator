package com.best.billing.departmen.customer;

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

    @Autowired
    public OperatorImpl(Resolution resolution, Calculator calculator, RoomEventService eventService) {
        this.resolution = resolution;
        this.calculator = calculator;
        this.eventService = eventService;
    }

    @Override
    public void getCalculations(@NonNull final LocalDate calculationPeriod, @NonNull final Long keyRoomId) {
        RoomEventsJournal eventJournal = this.eventService.getEventJournal(calculationPeriod, keyRoomId);
        calculator.calculate(calculationPeriod, resolution, eventJournal);
    }

    @Autowired
    public void getCalculations(@NonNull final LocalDate calculationPeriod) {
        List<RoomEventsJournal> eventJournals = this.eventService.getEventJournal(calculationPeriod);
        eventJournals.forEach(eventJournal -> calculator.calculate(calculationPeriod, resolution, eventJournal));
    }
}
