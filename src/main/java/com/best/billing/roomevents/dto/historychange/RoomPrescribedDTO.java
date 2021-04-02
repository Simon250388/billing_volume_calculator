package com.best.billing.roomevents.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RoomPrescribedDTO extends BaseHistoryDTO {
    Long keyRoomId;
    Integer prescribedCount;

    public RoomPrescribedDTO(Long id, Long version, String period, Long keyRoomId, Integer prescribedCount) {
        super(id, version, period);
        this.keyRoomId = keyRoomId;
        this.prescribedCount = prescribedCount;
    }
}
