package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountingPointServiceStateRepository extends CrudRepository<AccountingPointServiceState, Long>  {

    @Query(value = "from AccountingPointServiceState c" +
            " where (c.accountingPointKeyRoomServiceEntity, c.period) IN (" +
            "   select " +
            "   c.accountingPointKeyRoomServiceEntity" +
            "   ,Max(c.period)" +
            "   from AccountingPointServiceState c" +
            "   where c.accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id =:keyRoomId" +
            ")" +
            " AND c.active = true")
    Iterable<AccountingPointServiceState> findAllActiveByKeyRoomId(@Param("keyRoomId") long keyRoomId);
}
