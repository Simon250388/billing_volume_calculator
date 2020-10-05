package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.basemodels.BaseHistoty;
import com.best.billingvolumecalculator.models.entity.KeyRoom;

import javax.persistence.*;

/**
 * История изменения проживающих
 */
@Entity
@Table(name = "room_residents")
public class RoomResident extends BaseHistoty {
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
