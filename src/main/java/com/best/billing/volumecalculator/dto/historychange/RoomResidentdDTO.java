package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class RoomResidentdDTO extends BaseHistoryDTO{
    private Long keyRoomId;
    private Integer residentCount;

    public RoomResidentdDTO(Long id, String period, Long keyRoomId, Integer residentCount) {
        super(id, period);
        this.keyRoomId = keyRoomId;
        this.residentCount = residentCount;
    }
}
