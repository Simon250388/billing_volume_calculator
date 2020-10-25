package com.best.billing.servicebuilder.services.historychange;

import com.best.billing.servicebuilder.dto.historychange.MeterValueDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

public interface MeterValueService extends BaseEntityService<MeterValueDTO> {
    Iterable<MeterValueDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
}
