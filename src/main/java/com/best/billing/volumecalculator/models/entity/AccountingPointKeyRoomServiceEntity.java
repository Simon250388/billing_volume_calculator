package com.best.billing.volumecalculator.models.entity;

import com.best.billing.volumecalculator.models.BaseEntity;
import com.best.billing.volumecalculator.models.catalog.DirectionOfUse;
import com.best.billing.volumecalculator.models.catalog.Service;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @ManyToOne()
    @JoinColumn(name = "accounting_point_key_room_id", nullable = false)
    private AccountingPointKeyRoom accountingPointKeyRoom;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "direction_of_use_id", nullable = false)
    private DirectionOfUse directionOfUse;
}
