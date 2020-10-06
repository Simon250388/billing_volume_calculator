package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.basemodels.BaseHistory;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Состояние услуги на точке учета
 */
@Entity
@Table(name = "accounting_point_service_state")
public class AccountingPointServiceState extends BaseHistory {
    /**
     * Ключ услуги на точке учета
     */
    @ManyToOne
    @JoinColumn(name = "accounting_point_Key_room_service_id")
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

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

    public AccountingPointKeyRoomServiceEntity getAccountingPointKeyRoomService() {
        return accountingPointKeyRoomServiceEntity;
    }

    public void setAccountingPointKeyRoomService(AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity) {
        this.accountingPointKeyRoomServiceEntity = accountingPointKeyRoomServiceEntity;
    }
}
