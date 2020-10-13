package com.best.billing.volumecalculator.repositories.historychange;

import com.best.billing.volumecalculator.models.historychange.AccountingPointServiceState;
import org.springframework.data.repository.CrudRepository;

public interface AccountingPointServiceStateRepository extends CrudRepository<AccountingPointServiceState, Long>  {

    Iterable<AccountingPointServiceState> findAllActiveByKeyRoomId(long keyRoomId);
}
