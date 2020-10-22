package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomOwnerDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface RoomOwnerService extends BaseEntityService<RoomOwnerDTO> {
    Optional<RoomOwnerDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
    Iterable<RoomOwnerDTO> doGetHistoryByKeyRoomId(@NotNull final Long keyRoomId);
}
