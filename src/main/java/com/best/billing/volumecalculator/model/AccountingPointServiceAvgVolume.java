package com.best.billing.volumecalculator.model;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.Service;
import com.best.billing.roomevents.models.AccountingPointKeyRoomServiceEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Средний объем потребления услуги на точке учета
 */
@Data
@Builder
@Entity
@Table(name = "accounting_point_service_avg_volume")
public class AccountingPointServiceAvgVolume implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(nullable = false)
    private LocalDate calculationPeriod;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_key_room_service_entity_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_part_id")
    private Service servicePart;
    @Column(nullable = false)
    private Long avgVolume;
}
