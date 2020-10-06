package com.best.billing.volumecalculator.models.entity;

import com.best.billing.volumecalculator.basemodels.BaseEntity;
import com.best.billing.volumecalculator.models.catalog.AccountingPoint;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Токи учета ключа помещения
 */
@Entity
@Table(name = "accounting_point_key_room")
public class AccountingPointKeyRoom extends BaseEntity {
    /**
     * Ключ помещения
     */
    @ManyToOne
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    /**
     * Точка учета
     */
    @ManyToOne
    @JoinColumn(name = "accounting_point_id", nullable = false)
    private AccountingPoint accountingPoint;

    public AccountingPoint getAccountingPoint() {
        return accountingPoint;
    }

    public void setAccountingPoint(AccountingPoint accountingPoint) {
        this.accountingPoint = accountingPoint;
    }

    public KeyRoom getKeyRoom() {
        return keyRoom;
    }

    public void setKeyRoom(KeyRoom keyRoom) {
        this.keyRoom = keyRoom;
    }
}
