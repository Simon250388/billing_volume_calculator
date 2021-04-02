package com.best.billing.roomevents.services.historychange;

import com.best.billing.roomevents.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

import java.util.Optional;

public interface AccountingPointMeterStateService extends BaseEntityService<AccountingPointMeterStateDTO> {

    Iterable<AccountingPointMeterStateDTO> doGetHistoryByAccountingPointKeyRoomService(@NonNull final Long accountingPointKeyRoomService, @NonNull final Long meterId);

    Optional<AccountingPointMeterStateDTO> doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(@NonNull final Long accountingPointKeyRoomServiceId, @NonNull final Long meterId);

    Iterable<AccountingPointMeterStateDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId);
}
