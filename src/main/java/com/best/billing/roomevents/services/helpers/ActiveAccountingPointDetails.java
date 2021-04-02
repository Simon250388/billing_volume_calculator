package com.best.billing.roomevents.services.helpers;

import com.best.billing.roomevents.dto.helpers.ActiveAccountingPointDetailsDTO;
import lombok.NonNull;


public interface ActiveAccountingPointDetails {
    Iterable<ActiveAccountingPointDetailsDTO> doGetAllActiveByKeyRoomId(@NonNull Long keyRoomId);
}
