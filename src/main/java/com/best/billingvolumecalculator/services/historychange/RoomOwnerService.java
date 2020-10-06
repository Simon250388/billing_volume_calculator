package com.best.billingvolumecalculator.services.historychange;

import com.best.billingvolumecalculator.dto.changehistory.RoomOwnerDTO;
import com.best.billingvolumecalculator.models.historychange.RoomOwner;
import com.best.billingvolumecalculator.services.catalog.BaseEntityService;

import java.util.Optional;

public interface RoomOwnerService extends BaseEntityService<RoomOwner, RoomOwnerDTO> {
    Optional<RoomOwner> getLastByKeyRoomId(long keyRoomId);

    @Override
    RoomOwner save(RoomOwner accountingPoint);

    @Override
    Optional<RoomOwnerDTO> findById(long id);
}
