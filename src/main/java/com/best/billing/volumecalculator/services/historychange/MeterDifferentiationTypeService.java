package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.MeterDifferentiationTypeDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;
import org.jetbrains.annotations.NotNull;

public interface MeterDifferentiationTypeService extends BaseEntityService<MeterDifferentiationTypeDTO> {
    Iterable<MeterDifferentiationTypeDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
}
