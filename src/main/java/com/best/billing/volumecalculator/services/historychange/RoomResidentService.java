package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomResidentdDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;

import java.util.Optional;

public interface RoomResidentService extends BaseEntityService<RoomResidentdDTO> {
    Iterable<RoomResidentdDTO> doGetHistoryByKeyRoomId(long keyRoomId);

    Optional<RoomResidentdDTO> doGetLastByKeyRoomId(long keyRoomId);
}
