package com.best.billing.departmen.customer;

import java.util.List;

public interface RoomEventsJournal {
    Long getKeyRoomId();
    List<RoomEvent> getOrderEvents();
    RoomProperties getRoomProperties(RoomEvent roomEvent);
}
