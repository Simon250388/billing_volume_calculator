package com.best.billing.servicebuilder.services.helpers;

import com.best.billing.servicebuilder.dto.helpers.KeyRoomDetailPropertyDTO;

public interface KeyRoomDetailProperty {
    KeyRoomDetailPropertyDTO doGetLastDetails(Long keyRoomId);
}
