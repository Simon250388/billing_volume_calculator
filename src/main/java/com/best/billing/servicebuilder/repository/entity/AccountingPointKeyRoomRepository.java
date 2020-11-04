package com.best.billing.servicebuilder.repository.entity;

import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountingPointKeyRoomRepository extends CrudRepository<AccountingPointKeyRoom, Long> {
    List<AccountingPointKeyRoom> findAllByKeyRoom(@NonNull Long keyRoomId);
}
