package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountingPointServiceStateRepository extends CrudRepository<AccountingPointServiceState, Long>  {

    @Query(value = "FROM AccountingPointServiceState c" +
            " WHERE (c.accountingPointKeyRoomServiceEntity, c.period) IN (" +
            "   SELECT " +
            "   c.accountingPointKeyRoomServiceEntity" +
            "   ,MAX(c.period)" +
            "   FROM AccountingPointServiceState c" +
            "   WHERE c.accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id =:keyRoomId" +
            "   GROUP BY c.accountingPointKeyRoomServiceEntity" +
            ")" +
            " AND c.active = true")
    Iterable<AccountingPointServiceState> findAllActiveByKeyRoomId(@Param("keyRoomId") long keyRoomId);
}
