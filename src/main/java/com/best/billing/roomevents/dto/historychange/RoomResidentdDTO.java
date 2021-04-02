package com.best.billing.roomevents.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class RoomResidentdDTO extends BaseHistoryDTO{
    Long keyRoomId;
    Integer residentCount;

    public RoomResidentdDTO(Long id, Long version, String period, Long keyRoomId, Integer residentCount) {
        super(id, version, period);
        this.keyRoomId = keyRoomId;
        this.residentCount = residentCount;
    }
}
