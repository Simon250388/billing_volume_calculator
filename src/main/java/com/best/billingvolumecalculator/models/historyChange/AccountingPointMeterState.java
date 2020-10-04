package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.basemodels.BaseHistoty;
import com.best.billingvolumecalculator.models.catalog.Meter;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoomService;
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
public class AccountingPointMeterState extends BaseHistoty {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id" )
    private AccountingPointKeyRoomService accountingPointKeyRoomService;
    @ManyToOne
    @JoinColumn(name = "meter_id" )
    private Meter meter;
    @ManyToOne
    @JoinColumn(name = "meter_state_id" )
    private MeterState meterState;

    public AccountingPointKeyRoomService getAccountingPointKeyRoomService() {
        return accountingPointKeyRoomService;
    }

    public void setAccountingPointKeyRoomService(AccountingPointKeyRoomService accountingPointKeyRoomService) {
        this.accountingPointKeyRoomService = accountingPointKeyRoomService;
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
