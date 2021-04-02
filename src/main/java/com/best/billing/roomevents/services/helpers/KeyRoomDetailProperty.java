package com.best.billing.roomevents.services.helpers;

import com.best.billing.roomevents.dto.helpers.KeyRoomDetailPropertyDTO;

public interface KeyRoomDetailProperty {
    KeyRoomDetailPropertyDTO doGetLastDetails(Long keyRoomId);
}
