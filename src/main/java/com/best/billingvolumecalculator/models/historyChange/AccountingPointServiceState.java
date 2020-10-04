package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.basemodels.BaseHistoty;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoomService;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Состояние услуги на точке учета
 */
@Entity
@Table(name = "accounting_point_service_state")
public class AccountingPointServiceState extends BaseHistoty {
    /**
     * Ключ услуги на точке учета
     */
    @ManyToOne
    @JoinColumn(name = "accounting_point_Key_room_service_id")
    private AccountingPointKeyRoomService accountingPointKeyRoomService;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Состояние услуги
     */
    private boolean active;

    public AccountingPointKeyRoomService getAccountingPointKeyRoomService() {
        return accountingPointKeyRoomService;
    }

    public void setAccountingPointKeyRoomService(AccountingPointKeyRoomService accountingPointKeyRoomService) {
        this.accountingPointKeyRoomService = accountingPointKeyRoomService;
    }
}
