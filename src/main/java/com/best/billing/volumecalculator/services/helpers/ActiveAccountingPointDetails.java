package com.best.billing.volumecalculator.services.helpers;

import com.best.billing.volumecalculator.dto.helpers.ActiveAccountingPointDetailsDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface ActiveAccountingPointDetails {
    Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NotNull Long keyRoomId);
}
