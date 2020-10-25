package com.best.billing.servicebuilder.dto.entity;

import com.best.billing.base.dto.BaseEntityDTO;
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
