package com.best.billing.roomevents.service;

import com.best.billing.departmen.customer.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomEventServiceImpl implements RoomEventService {

    private final List<RoomEventRepository> roomEventRepositories;

    @Autowired
    public RoomEventServiceImpl(List<RoomEventRepository> roomEventRepositories) {
        this.roomEventRepositories = roomEventRepositories;
    }

    @Override
    public RoomEventsJournal getEventJournal(@NonNull final LocalDate CalculationPeriod, @NonNull final Long keyRoomId) {
        List<RoomEvent> orderEvents = getOrderByPeriodEvents(CalculationPeriod, keyRoomId);
        RoomProperties roomProperties = RoomProperties.builder().build();
        return new RoomEventsJournalImpl(keyRoomId, roomProperties, orderEvents);
    }

    @Override
    public List<RoomEventsJournal> getEventJournal(LocalDate CalculationPeriod) {
        return null;
    }

    private List<RoomEvent> getOrderByPeriodEvents(final LocalDate CalculationPeriod, final long keyRoomId) {
        return this.roomEventRepositories.stream()
                .flatMap(repository ->
                        repository.getEvents(keyRoomId, CalculationPeriod).stream())
                .sorted(Comparator.comparing(RoomEvent::getPeriod))
                .collect(Collectors.toList());
    }
}
