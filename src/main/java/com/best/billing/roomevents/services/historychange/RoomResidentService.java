package com.best.billing.roomevents.services.historychange;

import com.best.billing.roomevents.dto.historychange.RoomResidentdDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

import java.util.Optional;

public interface RoomResidentService extends BaseEntityService<RoomResidentdDTO> {
    Iterable<RoomResidentdDTO> doGetHistoryByKeyRoomId(@NonNull final Long keyRoomId);

    Optional<RoomResidentdDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId);
}
