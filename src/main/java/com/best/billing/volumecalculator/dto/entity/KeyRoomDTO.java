package com.best.billing.volumecalculator.dto.entity;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class KeyRoomDTO extends BaseEntityDTO{
    private Long buildingId;
    private Long roomId;
    private boolean privateSector;
}
