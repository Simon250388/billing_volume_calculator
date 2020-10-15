package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.catalog.Meter;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * История показаний прибора учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meter_values")
public class MeterValue extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id" )
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne
    @JoinColumn(name = "meter_id" )
    private Meter meter;
    @Column(nullable = false)
    private int value;
}
