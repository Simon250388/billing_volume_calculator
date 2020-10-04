package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.models.catalog.ImprovementType;
import com.best.billingvolumecalculator.models.catalog.Service;
import com.best.billingvolumecalculator.models.entity.KeyRoom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Вид благоустройства для услуг помещения
 */
@Entity
@Table(name = "room_improvement_types")
public class RoomImprovementType {
    @ManyToOne
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne
    @JoinColumn(name = "improvement_type_id", nullable = false)
    private ImprovementType improvementType;

    public KeyRoom getKeyRoom() {
        return keyRoom;
    }

    public void setKeyRoom(KeyRoom keyRoom) {
        this.keyRoom = keyRoom;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ImprovementType getImprovementType() {
        return improvementType;
    }

    public void setImprovementType(ImprovementType improvementType) {
        this.improvementType = improvementType;
    }
}
