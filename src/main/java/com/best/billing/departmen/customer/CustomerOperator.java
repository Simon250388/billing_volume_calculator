package com.best.billing.departmen.customer;

import lombok.NonNull;

import java.time.LocalDate;

public interface CustomerOperator {
    void printCalculations(@NonNull LocalDate calculationPeriod, @NonNull RoomEventsJournal eventsJournal);
}
