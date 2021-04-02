package com.best.billing.departmen.customer;

import com.best.billing.volumecalculator.service.Calculator;
import com.best.billing.resolution.Resolution;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerOperatorImpl implements CustomerOperator {

    private final Resolution resolution;
    private final Calculator calculator;

    @Autowired
    public CustomerOperatorImpl(Resolution resolution, Calculator calculator) {
        this.resolution = resolution;
        this.calculator = calculator;
    }

    @Autowired
    public void printCalculations(@NonNull final LocalDate calculationPeriod, @NonNull final RoomEventsJournal eventsJournal) {
        calculator.calculate(calculationPeriod, resolution,  eventsJournal);
    }
}
