package com.best.billingvolumecalculator.models.entity;

import com.best.billingvolumecalculator.basemodels.BaseEntity;
import com.best.billingvolumecalculator.models.catalog.Building;
import com.best.billingvolumecalculator.models.catalog.Room;

import javax.persistence.*;

/**
 * Ключ помещения
 */
@Entity
@Table(name = "key_rooms")
public class KeyRoom extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
    @OneToOne
    @JoinColumn(name = "room_id", nullable = true)
    private Room room;
    /**
     * Признак частного сектора
     */
    @Column(nullable = false)
    private boolean privateSector;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isPrivateSector() {
        return privateSector;
    }

    public void setPrivateSector(boolean privateSector) {
        this.privateSector = privateSector;
    }
}
