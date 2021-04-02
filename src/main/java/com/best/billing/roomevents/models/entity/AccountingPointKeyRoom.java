package com.best.billing.roomevents.models.entity;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.AccountingPoint;
import lombok.*;

import javax.persistence.*;

/**
 * Токи учета ключа помещения
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "accounting_point_key_room")
public class AccountingPointKeyRoom implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
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