package com.best.billing.roomevents.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RoomOwnerDTO extends BaseHistoryDTO {
    Long keyRoomId;
    Integer ownerCount;

    public RoomOwnerDTO(Long id, Long version, String period, Long keyRoomId, Integer ownerCount) {
        super(id, version, period);
        this.keyRoomId = keyRoomId;
        this.ownerCount = ownerCount;
    }

}
