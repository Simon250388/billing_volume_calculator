package com.best.billingvolumecalculator.services.entity;

import com.best.billingvolumecalculator.dto.entity.KeyRoomDTO;
import com.best.billingvolumecalculator.models.entity.KeyRoom;
import com.best.billingvolumecalculator.services.catalog.BaseEntityService;

import java.util.Optional;

public interface KeyRoomService extends BaseEntityService<KeyRoom, KeyRoomDTO> {
    @Override
    KeyRoom save(KeyRoom accountingPoint);

    @Override
    Optional<KeyRoomDTO> findById(long id);
}
