package com.best.billing.roomevents.service;

import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomEventsJournal;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.metervalues.model.MeterValue;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

class RoomEventsJournalImpl implements RoomEventsJournal {

    private final List<RoomEvent> orderEvents;
    private final List<MeterValue> meterValues;
    private final long keyRoomId;
    private final Map<RoomEvent, RoomProperties> roomEventListMap = new HashMap<>();
    private boolean isInit = false;

    RoomEventsJournalImpl(@NonNull final Long keyRoomId, @NonNull final List<RoomEvent> orderEvents, @NonNull List<MeterValue> meterValues) {
        this.keyRoomId = keyRoomId;
        this.orderEvents = orderEvents;
        this.meterValues = meterValues;
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
        List<MeterValue> meterValuesByEvent = Collections.emptyList();
        for (RoomEvent event : this.orderEvents) {
            if (event.isProvideMeterValue()) {
                meterValuesByEvent = meterValues.stream().filter(meterValue ->
                        meterValue.getMethodProvideMeterValue().equals(event.getMethodProvideMeterValue())
                                && meterValue.getEventId() == event.getId())
                        .collect(Collectors.toList());
            } else {
                // TODO get last provide meter values on event
            }
            roomProperties = event.register(roomProperties, previousEvent, meterValuesByEvent);
            roomEventListMap.put(event, roomProperties);
            previousEvent = event;
        }
        this.isInit = true;
    }
}
