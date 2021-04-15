package com.best.billing.roomevents.service;

import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomEventsJournal;
import com.best.billing.departmen.customer.RoomProperties;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RoomEventsJournalImpl implements RoomEventsJournal {

    private final List<RoomEvent> orderEvents;
    private final long keyRoomId;
    private final Map<RoomEvent, RoomProperties> roomEventListMap = new HashMap<>();
    private boolean isInit = false;

    RoomEventsJournalImpl(@NonNull final Long keyRoomId, @NonNull final List<RoomEvent> orderEvents) {
        this.keyRoomId = keyRoomId;
        this.orderEvents = orderEvents;
    }

    @Override
    public Long getKeyRoomId() {
        return this.keyRoomId;
    }

    @Override
    public List<RoomEvent> getOrderEvents() {
        return this.orderEvents;
    }

    @Override
    public RoomProperties getRoomProperties(RoomEvent roomEvent) {
        if (!this.isInit) {
            initRoomProperties();
        }
        return this.roomEventListMap.get(roomEvent);
    }

    private void initRoomProperties() {
        RoomProperties roomProperties = null;
        RoomEvent previousEvent = null;
        for (RoomEvent event : this.orderEvents) {
            roomProperties = event.register(roomProperties, previousEvent);
            roomEventListMap.put(event, roomProperties);
            previousEvent = event;

        }
        this.isInit = true;
    }
}
