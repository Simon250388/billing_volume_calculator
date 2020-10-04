package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.models.entity.KeyRoom;

import javax.persistence.*;

/**
 * История изменения количества собственников
 */
@Entity
@Table(name = "room_owners")
public class RoomOwner {
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
