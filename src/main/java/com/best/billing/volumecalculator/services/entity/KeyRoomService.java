package com.best.billing.volumecalculator.services.entity;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.services.BaseEntityService;

import java.util.Optional;

public interface KeyRoomService extends BaseEntityService<KeyRoom, KeyRoomDTO> {
    @Override
    KeyRoomDTO save(KeyRoom accountingPoint);
    @Override
    Optional<KeyRoomDTO> findById(long id);
    Iterable<KeyRoomDTO> findAll();
    Iterable<KeyRoomDTO> findAll(long buildingId);
    Iterable<KeyRoomDTO> findAll(long buildingId, long roomId);

}
