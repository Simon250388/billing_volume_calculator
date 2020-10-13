package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class RoomPrescribedDTO extends BaseHistoryDTO {
    Long keyRoomId;
    Integer prescribedCount;

    public RoomPrescribedDTO(Long id, String period, Long keyRoomId, Integer prescribedCount) {
        super(id, period);
        this.keyRoomId = keyRoomId;
        this.prescribedCount = prescribedCount;
    }
}
