package com.best.billing.departmen.customer;

import lombok.NonNull;

import java.time.LocalDate;

public interface Operator {
    void printCalculations(@NonNull LocalDate calculationPeriod, @NonNull RoomEventsJournal eventsJournal);
}
