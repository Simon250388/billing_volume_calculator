package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.basemodels.BaseHistoty;
import com.best.billingvolumecalculator.models.catalog.Meter;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;

import javax.persistence.*;

/**
 * История показаний прибора учета
 */
@Entity
@Table(name = "meter_values")
public class MeterValue extends BaseHistoty {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id" )
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne
    @JoinColumn(name = "meter_id" )
    private Meter meter;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Column(nullable = false)
    private int value;

    public AccountingPointKeyRoomServiceEntity getAccountingPointKeyRoomService() {
        return accountingPointKeyRoomServiceEntity;
    }

    public void setAccountingPointKeyRoomService(AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity) {
        this.accountingPointKeyRoomServiceEntity = accountingPointKeyRoomServiceEntity;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

}
