package com.best.billing.servicebuilder.services.historychange;

import com.best.billing.servicebuilder.dto.historychange.RoomOwnerDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface RoomOwnerService extends BaseEntityService<RoomOwnerDTO> {
    Optional<RoomOwnerDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
    Iterable<RoomOwnerDTO> doGetHistoryByKeyRoomId(@NotNull final Long keyRoomId);
}
