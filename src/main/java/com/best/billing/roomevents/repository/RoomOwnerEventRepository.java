package com.best.billing.roomevents.repository;

import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoomOwnerEventRepository implements RoomEventRepository {

    private final RoomOwnerRepository repository;

    @Autowired
    public RoomOwnerEventRepository(RoomOwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoomEvent> getEvents(Long keyRoomId, LocalDate calculationPeriod) {
        List<RoomEvent> result = new ArrayList<>();
        repository.findAllByKeyRoomIdAndPeriodBetween(keyRoomId, calculationPeriod, calculationPeriod).forEach(result::add);
        return result;
    }
}
