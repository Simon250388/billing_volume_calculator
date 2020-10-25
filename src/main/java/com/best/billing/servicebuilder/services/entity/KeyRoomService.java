package com.best.billing.servicebuilder.services.entity;

import com.best.billing.servicebuilder.dto.entity.KeyRoomDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

public interface KeyRoomService extends BaseEntityService<KeyRoomDTO> {
    Iterable<KeyRoomDTO> findAll();
    Iterable<KeyRoomDTO> findAll(@NotNull final Long buildingId);
    Iterable<KeyRoomDTO> findAll(@NotNull final Long buildingId, @NotNull final Long roomId);
}
