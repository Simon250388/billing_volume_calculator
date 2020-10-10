package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomOwnerDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;

import java.util.Optional;

public interface RoomOwnerService extends BaseEntityService<RoomOwnerDTO> {
    Optional<RoomOwnerDTO> doGetLastByKeyRoomId(long keyRoomId);
    Iterable<RoomOwnerDTO> doGetHistoryByKeyRoomId(long keyRoomId);
}
