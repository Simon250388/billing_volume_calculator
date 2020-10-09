package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountingPointMeterStateRepository extends CrudRepository<AccountingPointMeterState, Long> {
    // TODO FIX ME
    @Query(value = "")
    Optional<AccountingPointMeterState> findLastByAccountingPointKeyRoomServiceIdAndMeterId(long accountingPointKeyRoomService, long meterId);

    Iterable<AccountingPointMeterState>  findAllByAccountingPointKeyRoomServiceIdAndMeterId(long accountingPointKeyRoomServiceId, long meterId);
}
