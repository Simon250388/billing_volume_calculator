package com.best.billing.roomevents.services.historychange;

import com.best.billing.roomevents.dto.historychange.MeterValueDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;


public interface MeterValueService extends BaseEntityService<MeterValueDTO> {
    Iterable<MeterValueDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId);
}
