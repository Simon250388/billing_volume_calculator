package com.best.billingvolumecalculator.models.entity;

import com.best.billingvolumecalculator.basemodels.BaseEntity;
import com.best.billingvolumecalculator.models.catalog.Service;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Услуга точки учета (без учета состояния)
 */
@Entity
@Table(name = "accounting_point_key_room_services")
public class AccountingPointKeyRoomService extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_id", nullable = false)
    private AccountingPointKeyRoom accountingPointKeyRoom;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public AccountingPointKeyRoom getAccountingPointKeyRoom() {
        return accountingPointKeyRoom;
    }

    public void setAccountingPointKeyRoom(AccountingPointKeyRoom accountingPointKeyRoom) {
        this.accountingPointKeyRoom = accountingPointKeyRoom;
    }
}
