package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.basemodels.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;

import javax.persistence.*;

/**
 * История изменения количества собственников
 */
@Entity
@Table(name = "room_owners")
public class RoomOwner extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int ownerCount;

    public KeyRoom getKeyRoom() {
        return keyRoom;
    }

    public void setKeyRoom(KeyRoom keyRoom) {
        this.keyRoom = keyRoom;
    }

    public int getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(int ownerCount) {
        this.ownerCount = ownerCount;
    }
}
