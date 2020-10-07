package com.best.billing.volumecalculator.services.entity;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.services.catalog.BaseEntityService;

import java.util.Optional;

public interface KeyRoomService extends BaseEntityService<KeyRoom, KeyRoomDTO> {
    @Override
    KeyRoom save(KeyRoom accountingPoint);

    @Override
    Optional<KeyRoomDTO> findById(long id);
}
