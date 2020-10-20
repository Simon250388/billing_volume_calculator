package com.best.billing.volumecalculator.services.entity;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface KeyRoomService extends BaseEntityService<KeyRoomDTO> {
    Iterable<KeyRoomDTO> findAll();
    Iterable<KeyRoomDTO> findAll(@NotNull final Long buildingId);
    Iterable<KeyRoomDTO> findAll(@NotNull final Long buildingId, @NotNull final Long roomId);
}
