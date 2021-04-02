package com.best.billing.roomevents.services.historychange;

import com.best.billing.roomevents.dto.historychange.MeterDifferentiationTypeDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

public interface MeterDifferentiationTypeService extends BaseEntityService<MeterDifferentiationTypeDTO> {
    Iterable<MeterDifferentiationTypeDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId);
}
