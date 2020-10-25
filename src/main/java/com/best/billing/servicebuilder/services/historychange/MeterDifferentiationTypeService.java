package com.best.billing.servicebuilder.services.historychange;

import com.best.billing.servicebuilder.dto.historychange.MeterDifferentiationTypeDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

public interface MeterDifferentiationTypeService extends BaseEntityService<MeterDifferentiationTypeDTO> {
    Iterable<MeterDifferentiationTypeDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
}
