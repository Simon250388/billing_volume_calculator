package com.best.billing.servicebuilder.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class RoomResidentdDTO extends BaseHistoryDTO{
    Long keyRoomId;
    Integer residentCount;

    public RoomResidentdDTO(Long id, String period, Long keyRoomId, Integer residentCount) {
        super(id, period);
        this.keyRoomId = keyRoomId;
        this.residentCount = residentCount;
    }
}
