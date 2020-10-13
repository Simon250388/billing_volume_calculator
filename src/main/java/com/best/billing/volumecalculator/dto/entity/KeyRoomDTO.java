package com.best.billing.volumecalculator.dto.entity;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class KeyRoomDTO extends BaseEntityDTO{
    Long buildingId;
    Long roomId;
    boolean privateSector;

    public KeyRoomDTO(Long id, Long buildingId, Long roomId, boolean privateSector) {
        super(id);
        this.buildingId = buildingId;
        this.roomId = roomId;
        this.privateSector = privateSector;
    }
}
