package com.best.billingvolumecalculator.models.historychange;

import com.best.billingvolumecalculator.basemodels.BaseHistory;
import com.best.billingvolumecalculator.models.catalog.RateGroup;
import com.best.billingvolumecalculator.models.catalog.Service;
import com.best.billingvolumecalculator.models.entity.KeyRoom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Тарифные группы услуг помещения
 */
@Entity
@Table(name = "room_rate_groups")
public class RoomRateGroup  extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne
    @JoinColumn(name = "rate_group_id", nullable = false)
    private RateGroup rateGroup;

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

    public RateGroup getRateGroup() {
        return rateGroup;
    }

    public void setRateGroup(RateGroup rateGroup) {
        this.rateGroup = rateGroup;
    }
}
