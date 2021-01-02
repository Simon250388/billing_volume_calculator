package com.best.billing.servicebuilder.models.entity;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Токи учета ключа помещения
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_point_key_room")
public class AccountingPointKeyRoom extends BaseEntity {
    /**
     * Ключ помещения
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    /**
     * Точка учета
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_id", nullable = false)
    private AccountingPoint accountingPoint;
}
