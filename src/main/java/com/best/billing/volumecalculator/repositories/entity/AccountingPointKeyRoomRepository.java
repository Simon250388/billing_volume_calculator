package com.best.billing.volumecalculator.repositories.entity;

import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountingPointKeyRoomRepository extends CrudRepository<AccountingPointKeyRoom, Long> {
    List<AccountingPointKeyRoom> findAllByKeyRoom(long keyRoomId);
}
