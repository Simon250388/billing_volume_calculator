package com.best.billing.volumecalculator.model;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.Service;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Средний объем потребления услуги на точке учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_point_service_avg_volume")
public class AccountingPointServiceAvgVolume extends BaseEntity {
    @Column(nullable = false)
    private LocalDate calculationPeriod;
    @ManyToOne()
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne
    @JoinColumn(name = "service_part_id")
    private Service servicePart;
    @Column(nullable = false)
    private Integer avgVolume;
}
