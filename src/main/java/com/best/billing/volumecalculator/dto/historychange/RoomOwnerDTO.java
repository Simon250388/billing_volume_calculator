package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class RoomOwnerDTO extends BaseHistoryDTO {
    Long keyRoomId;
    Integer ownerCount;

    public RoomOwnerDTO(Long id, String period, Long keyRoomId, Integer ownerCount) {
        super(id, period);
        this.keyRoomId = keyRoomId;
        this.ownerCount = ownerCount;
    }
}
