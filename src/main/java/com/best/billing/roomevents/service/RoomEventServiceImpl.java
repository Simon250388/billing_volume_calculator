package com.best.billing.roomevents.service;

import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomEventRepository;
import com.best.billing.departmen.customer.RoomEventService;
import com.best.billing.departmen.customer.RoomEventsJournal;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    public RoomEventsJournal getEventJournal(@NonNull final LocalDate calculationPeriod, @NonNull final Long keyRoomId) {
        List<RoomEvent> orderEvents = getOrderByPeriodEvents(calculationPeriod, keyRoomId);
        return new RoomEventsJournalImpl(keyRoomId, orderEvents, Collections.emptyList());
    }

    @Override
    public List<RoomEventsJournal> getEventJournal(LocalDate calculationPeriod) {
        return Collections.emptyList();
    }

    private List<RoomEvent> getOrderByPeriodEvents(final LocalDate calculationPeriod, final long keyRoomId) {
        return this.roomEventRepositories.stream()
                .flatMap(repository ->
                        repository.getEvents(keyRoomId, calculationPeriod).stream())
                .sorted(Comparator.comparing(RoomEvent::getPeriod))
                .collect(Collectors.toList());
    }
}
