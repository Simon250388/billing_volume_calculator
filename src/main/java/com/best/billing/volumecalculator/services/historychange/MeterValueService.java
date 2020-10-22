package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.MeterValueDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;
import org.jetbrains.annotations.NotNull;

public interface MeterValueService extends BaseEntityService<MeterValueDTO> {
    Iterable<MeterValueDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
}
