package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountingPointMeterStateRepository extends CrudRepository<AccountingPointMeterState, Long> {

    @Query(value = "FROM AccountingPointMeterState " +
            " WHERE accountingPointKeyRoomServiceEntity =:accountingPointKeyRoomServiceEntityId" +
            " AND c.meter = :meterId" +
            " AND c.period IN (" +
            "       SELECT MAX(period)" +
            "       FROM AccountingPointMeterState" +
            "       WHERE accountingPointKeyRoomServiceEntity = :accountingPointKeyRoomServiceEntityId" +
            "       AND meter = :meterId" +
            ")")
    Optional<AccountingPointMeterState> findOneLastAccountingPointKeyRoomServiceEntityIdAndMeterId(
            @NonNull @Param("accountingPointKeyRoomServiceEntityId") Long accountingPointKeyRoomServiceEntityId,
            @NonNull @Param("meterId") Long meterId);

    @Query(value = "FROM AccountingPointMeterState " +
            "WHERE (accountingPointKeyRoomServiceEntity, meter, period) IN  ( " +
            "       SELECT accountingPointKeyRoomServiceEntity,meter,MAX(c.period)" +
            "       FROM AccountingPointMeterState " +
            "       WHERE accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom = :keyRoomId" +
            "       GROUP BY accountingPointKeyRoomServiceEntity,meter)")
    Iterable<AccountingPointMeterState> findAllLastByKeyRoomId(@NonNull @Param("keyRoomId") Long keyRoomId);

    Iterable<AccountingPointMeterState> findAllByAccountingPointKeyRoomServiceEntityIdAndMeterId(Long accountingPointKeyRoomServiceEntityId, Long meterId);
}
