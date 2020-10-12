package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoomResidentdDTO extends BaseHistoryDTO{
    private Long keyRoomId;
    private Integer residentCount;
}
