package com.best.billing.roomevents.services.entity;

import com.best.billing.roomevents.dto.entity.KeyRoomDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

public interface KeyRoomService extends BaseEntityService<KeyRoomDTO> {
    Iterable<KeyRoomDTO> findAll();
    Iterable<KeyRoomDTO> findAll(@NonNull final Long buildingId);
    Iterable<KeyRoomDTO> findAll(@NonNull final Long buildingId, @NonNull final Long roomId);
}
