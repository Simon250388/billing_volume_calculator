package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceState;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountingPointServiceStateRepository extends CrudRepository<AccountingPointServiceState, Long>  {

    @Query(name = AccountingPointServiceState.QUERY_FIND_ALL_LAST_ACTIVE_BY_KEY_ROOM_ID)
    @EntityGraph(value = "accounting-point-service-state-key-room-graph")
    Iterable<AccountingPointServiceState> findAllActiveByKeyRoomId(@NotNull @Param("keyRoomId") Long keyRoomId);
}
