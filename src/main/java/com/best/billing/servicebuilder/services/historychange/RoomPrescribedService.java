package com.best.billing.servicebuilder.services.historychange;

import com.best.billing.servicebuilder.dto.historychange.RoomPrescribedDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

import java.util.Optional;

public interface RoomPrescribedService extends BaseEntityService<RoomPrescribedDTO> {
    Optional<RoomPrescribedDTO> doGetLastByKeyRoomId(@NonNull final Long keyRoomId);
    Iterable<RoomPrescribedDTO> doGetHistoryByKeyRoomId(@NonNull final Long keyRoomId);

}
