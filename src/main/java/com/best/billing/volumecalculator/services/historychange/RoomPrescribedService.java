package com.best.billing.volumecalculator.services.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomPrescribedDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;

import java.util.Optional;

public interface RoomPrescribedService extends BaseEntityService<RoomPrescribedDTO> {
    Optional<RoomPrescribedDTO> doGetLastByKeyRoomId(long keyRoomId);
    Iterable<RoomPrescribedDTO> doGetHistoryByKeyRoomId(long keyRoomId);

}
