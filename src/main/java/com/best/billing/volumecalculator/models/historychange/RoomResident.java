package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.basemodels.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;

import javax.persistence.*;

/**
 * История изменения проживающих
 */
@Entity
@Table(name = "room_residents")
public class RoomResident extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int residentCount;

    public KeyRoom getKeyRoom() {
        return keyRoom;
    }

    public void setKeyRoom(KeyRoom keyRoom) {
        this.keyRoom = keyRoom;
    }

    public int getResidentCount() {
        return residentCount;
    }

    public void setResidentCount(int residentCount) {
        this.residentCount = residentCount;
    }
}
