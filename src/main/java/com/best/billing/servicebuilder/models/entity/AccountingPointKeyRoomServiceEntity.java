package com.best.billing.servicebuilder.models.entity;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import lombok.*;

import javax.persistence.*;

/**
 * Услуга точки учета (без учета состояния)
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "accounting_point_key_room_services")
public class AccountingPointKeyRoomServiceEntity implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_key_room_id", nullable = false)
    private AccountingPointKeyRoom accountingPointKeyRoom;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "direction_of_use_id", nullable = false)
    private DirectionOfUse directionOfUse;
}
