package com.best.billing.volumecalculator.dto.entity;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class KeyRoomDTO extends BaseEntityDTO {
    private long buildingId;
    private Long roomId;
    private boolean privateSector;
}
