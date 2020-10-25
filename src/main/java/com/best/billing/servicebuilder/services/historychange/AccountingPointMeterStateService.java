package com.best.billing.servicebuilder.services.historychange;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface AccountingPointMeterStateService extends BaseEntityService<AccountingPointMeterStateDTO> {

    Iterable<AccountingPointMeterStateDTO> doGetHistoryByAccountingPointKeyRoomService(@NotNull final Long accountingPointKeyRoomService, @NotNull final Long meterId);

    Optional<AccountingPointMeterStateDTO> doGetLastByAccountingPointKeyRoomServiceIdAndMeterId(@NotNull final Long accountingPointKeyRoomServiceId, @NotNull final Long meterId);

    Iterable<AccountingPointMeterStateDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
}
