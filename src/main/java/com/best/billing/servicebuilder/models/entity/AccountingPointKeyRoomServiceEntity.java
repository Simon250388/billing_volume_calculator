package com.best.billing.servicebuilder.models.entity;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Услуга точки учета (без учета состояния)
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_point_key_room_services")
public class AccountingPointKeyRoomServiceEntity extends BaseEntity {
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
