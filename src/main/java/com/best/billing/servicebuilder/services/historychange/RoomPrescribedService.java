package com.best.billing.servicebuilder.services.historychange;

import com.best.billing.servicebuilder.dto.historychange.RoomPrescribedDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface RoomPrescribedService extends BaseEntityService<RoomPrescribedDTO> {
    Optional<RoomPrescribedDTO> doGetLastByKeyRoomId(@NotNull final Long keyRoomId);
    Iterable<RoomPrescribedDTO> doGetHistoryByKeyRoomId(@NotNull final Long keyRoomId);

}
