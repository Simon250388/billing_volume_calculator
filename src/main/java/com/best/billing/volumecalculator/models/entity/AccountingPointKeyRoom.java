package com.best.billing.volumecalculator.models.entity;

import com.best.billing.volumecalculator.models.BaseEntity;
import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Токи учета ключа помещения
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
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
}
