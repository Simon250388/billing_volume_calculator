package com.best.billingvolumecalculator.repositories.entity;

import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountingPointKeyRoomRepository extends CrudRepository<AccountingPointKeyRoom, Long> {
    List<AccountingPointKeyRoom> findAllByKeyRoom(long KeyRoomId);
}
