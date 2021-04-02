package com.best.billing.departmen.customer;

import java.time.LocalDateTime;

public interface RoomEvent {
    LocalDateTime getPeriodFact();
    RoomProperties register(RoomProperties origin);
}
