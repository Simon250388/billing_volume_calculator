package com.best.billing.servicebuilder.services.helpers;

import com.best.billing.servicebuilder.dto.helpers.ActiveAccountingPointDetailsDTO;
import lombok.NonNull;


public interface ActiveAccountingPointDetails {
    Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NonNull Long keyRoomId);
}
