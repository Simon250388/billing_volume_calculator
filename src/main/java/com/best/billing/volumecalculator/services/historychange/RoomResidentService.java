package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomResidentdDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface RoomResidentService extends BaseEntityService<RoomResidentdDTO> {
    Iterable<RoomResidentdDTO> doGetHistoryByKeyRoomId(@NotNull final Long keyRoomId);

    Optional<RoomResidentdDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
}
