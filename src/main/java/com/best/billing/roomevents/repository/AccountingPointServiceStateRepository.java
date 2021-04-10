package com.best.billing.roomevents.repository;

import com.best.billing.roomevents.models.AccountingPointServiceState;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountingPointServiceStateRepository extends CrudRepository<AccountingPointServiceState, Long> {

    @Query("FROM AccountingPointServiceState " +
            "WHERE (accountingPointKeyRoomServiceEntity, period) IN ( " +
            "   SELECT accountingPointKeyRoomServiceEntity, MAX(period) " +
            "   FROM AccountingPointServiceState " +
            "   WHERE accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id = :keyRoomId " +
            "   GROUP BY accountingPointKeyRoomServiceEntity) " +
            "AND active = true")
    @EntityGraph(value = "accounting-point-service-state-key-room-graph")
    Iterable<AccountingPointServiceState> findAllActiveByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);
}
