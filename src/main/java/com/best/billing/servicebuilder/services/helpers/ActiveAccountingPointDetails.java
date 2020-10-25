package com.best.billing.servicebuilder.services.helpers;

import com.best.billing.servicebuilder.dto.helpers.ActiveAccountingPointDetailsDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface ActiveAccountingPointDetails {
    Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NotNull Long keyRoomId);
}
