package com.best.billing.roomevents.services.historychange;

import com.best.billing.roomevents.dto.historychange.RoomOwnerDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

import java.util.Optional;

public interface RoomOwnerService extends BaseEntityService<RoomOwnerDTO> {
    Optional<RoomOwnerDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId);
    Iterable<RoomOwnerDTO> doGetHistoryByKeyRoomId(@NonNull final Long keyRoomId);
}