package com.best.billing.servicebuilder.repositories.entity;

import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountingPointKeyRoomRepository extends CrudRepository<AccountingPointKeyRoom, Long> {
    List<AccountingPointKeyRoom> findAllByKeyRoom(@NotNull Long keyRoomId);
}
