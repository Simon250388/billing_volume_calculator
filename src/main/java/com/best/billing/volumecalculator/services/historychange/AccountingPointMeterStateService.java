package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.volumecalculator.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.services.BaseEntityService;

import java.util.Optional;

public interface AccountingPointMeterStateService extends BaseEntityService<AccountingPointMeterState, AccountingPointMeterStateDTO> {

    Iterable<AccountingPointMeterStateDTO> doGetHistoryByAccountingPointKeyRoomService(long accountingPointKeyRoomService, long meterId);

    Optional<AccountingPointMeterStateDTO> doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(long accountingPointKeyRoomServiceId, long meterId);
}
