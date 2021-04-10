package com.best.billing.roomevents.repository;

import com.best.billing.roomevents.models.AccountingPointKeyRoom;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountingPointKeyRoomRepository extends CrudRepository<AccountingPointKeyRoom, Long> {
    List<AccountingPointKeyRoom> findAllByKeyRoomId(@NonNull Long keyRoomId);
}
