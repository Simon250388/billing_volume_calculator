package com.best.billing.volumecalculator.dto.entity;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;

public class KeyRoomDTO extends BaseEntityDTO {
    private long buildingId;
    private Long roomId;
    private boolean privateSector;

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public boolean isPrivateSector() {
        return privateSector;
    }

    public void setPrivateSector(boolean privateSector) {
        this.privateSector = privateSector;
    }
}
