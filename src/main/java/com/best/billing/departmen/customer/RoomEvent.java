package com.best.billing.departmen.customer;

import java.time.LocalDateTime;

public interface RoomEvent {
    LocalDateTime getPeriodFact();
    LocalDateTime getPeriod();
    RoomProperties register(RoomProperties origin, RoomEvent previousEvent);
}
