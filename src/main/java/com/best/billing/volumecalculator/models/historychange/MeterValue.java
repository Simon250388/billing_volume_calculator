package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.basemodels.BaseHistory;
import com.best.billing.volumecalculator.models.catalog.Meter;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;

import javax.persistence.*;

/**
 * История показаний прибора учета
 */
@Entity
@Table(name = "meter_values")
public class MeterValue extends BaseHistory {
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
