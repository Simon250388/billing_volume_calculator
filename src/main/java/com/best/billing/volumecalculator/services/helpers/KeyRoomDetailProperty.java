package com.best.billing.volumecalculator.services.helpers;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;

import java.util.Optional;

public interface KeyRoomDetailProperty {
    Optional<KeyRoomDTO> doGetLastDetails(Long keyRoomId);
}
