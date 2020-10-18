package com.best.billing.volumecalculator.services.helpers;

import com.best.billing.volumecalculator.dto.helpers.KeyRoomDetailPropertyDTO;

public interface KeyRoomDetailProperty {
    KeyRoomDetailPropertyDTO doGetLastDetails(Long keyRoomId);
}
