package com.best.billing.departmen.customer;

import com.best.billing.roomevents.models.entity.KeyRoom;

import java.util.List;

public interface RoomEventsJournal {
    KeyRoom getKeyRoom();
    List<RoomEvent> getOrderEvents();
    List<RoomEvent> getMeterValueEvents();
    List<AccountingPointProperties> getAccountingPointsProperties(RoomEvent roomEvent);
}
