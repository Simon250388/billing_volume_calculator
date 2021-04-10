package com.best.billing.departmen.customer;

import java.time.LocalDate;
import java.util.List;

public interface RoomEventRepository {
    List<RoomEvent> getEvents(Long keyRoomId, LocalDate calculationPeriod);
}
