package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;

public class RoomOwnerDTO extends BaseHistoryDTO {
    private long keyRoomId;
    private int ownerCount;

    public long getKeyRoomId() {
        return keyRoomId;
    }

    public void setKeyRoomId(long keyRoomId) {
        this.keyRoomId = keyRoomId;
    }

    public int getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(int ownerCount) {
        this.ownerCount = ownerCount;
    }
}
