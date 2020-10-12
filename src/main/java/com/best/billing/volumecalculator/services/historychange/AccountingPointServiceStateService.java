package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointServiceStateDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;

public interface AccountingPointServiceStateService extends BaseEntityService<AccountingPointServiceStateDTO> {
    Iterable<AccountingPointServiceStateDTO> doGetAllActiveByKeyRoomId(long keyRoomId);
}
