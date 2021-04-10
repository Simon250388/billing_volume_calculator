package com.best.billing.departmen.customer;

import java.time.LocalDate;
import java.util.List;

public interface RoomEventService {
    RoomEventsJournal getEventJournal(LocalDate CalculationPeriod, Long keyRoomId);
    List<RoomEventsJournal> getEventJournal(LocalDate CalculationPeriod);
}
