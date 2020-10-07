package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomOwnerDTO;
import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import com.best.billing.volumecalculator.services.catalog.BaseEntityService;

import java.util.Optional;

public interface RoomOwnerService extends BaseEntityService<RoomOwner, RoomOwnerDTO> {
    Optional<RoomOwner> getLastByKeyRoomId(long keyRoomId);

    @Override
    RoomOwner save(RoomOwner accountingPoint);

    @Override
    Optional<RoomOwnerDTO> findById(long id);
}
