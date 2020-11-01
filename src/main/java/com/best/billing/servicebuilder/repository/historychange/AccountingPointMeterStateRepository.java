package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountingPointMeterStateRepository extends CrudRepository<AccountingPointMeterState, Long> {

    @Query(value = "SELECT c" +
            " FROM AccountingPointMeterState c" +
            " WHERE" +
            " c.accountingPointKeyRoomServiceEntity =:accountingPointKeyRoomServiceEntityId" +
            " AND c.meter =:meterId" +
            " AND c.period = (" +
            "       SELECT MAX(c.period)" +
            "       FROM AccountingPointMeterState c" +
            "       WHERE c.accountingPointKeyRoomServiceEntity =:accountingPointKeyRoomServiceEntityId" +
            "       AND c.meter =:meterId" +
            ")")
    Optional<AccountingPointMeterState> findOneLastAccountingPointKeyRoomServiceEntityIdAndMeterId(
            @NotNull @Param("accountingPointKeyRoomServiceEntityId") Long accountingPointKeyRoomServiceEntityId,
            @NotNull @Param("meterId") Long meterId);

    @Query(value = "SELECT c" +
            " FROM AccountingPointMeterState c" +
            " WHERE (c.accountingPointKeyRoomServiceEntity, c.meter, c.period) IN  (" +
            "       SELECT" +
            "           c.accountingPointKeyRoomServiceEntity" +
            "           ,c.meter" +
            "           ,MAX(c.period)" +
            "       FROM AccountingPointMeterState c" +
            "       WHERE" +
            "           c.accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom =:keyRoomId" +
            "       GROUP BY" +
            "           c.accountingPointKeyRoomServiceEntity" +
            "           ,c.meter)")
    Iterable<AccountingPointMeterState> findAllLastByKeyRoomId(@NotNull @Param("keyRoomId") Long keyRoomId);

    Iterable<AccountingPointMeterState> findAllByAccountingPointKeyRoomServiceEntityIdAndMeterId(Long accountingPointKeyRoomServiceEntityId, Long meterId);
}
