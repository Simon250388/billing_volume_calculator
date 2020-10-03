package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.basemodels.BaseHistoty;
import com.best.billingvolumecalculator.models.catalog.Meter;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoomService;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Прибор установленный на точке учета (без учета состояния)
 */
@Entity
@Table(name = "accounting_point_meters")
public class AccountingPointMeter extends BaseHistoty {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id" )
    private AccountingPointKeyRoomService accountingPointKeyRoomService;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

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
}
