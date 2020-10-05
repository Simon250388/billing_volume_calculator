package com.best.billingvolumecalculator.models.historychange;

import com.best.billingvolumecalculator.basemodels.BaseHistory;
import com.best.billingvolumecalculator.models.catalog.Meter;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billingvolumecalculator.models.enums.MeterState;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Состояние прибора учета на точке учета
 */
@Entity
@Table(name = "accounting_point_meter_states")
public class AccountingPointMeterState extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id" )
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne
    @JoinColumn(name = "meter_id" )
    private Meter meter;
    @ManyToOne
    @JoinColumn(name = "meter_state_id" )
    private MeterState meterState;

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

    public MeterState getMeterState() {
        return meterState;
    }

    public void setMeterState(MeterState meterState) {
        this.meterState = meterState;
    }

}
