package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.catalog.DifferentiationType;
import com.best.billing.volumecalculator.models.catalog.Meter;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
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
 * История изменения вида диффиринцироованности
 * прибора учета на точке учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meter_differentiation_type")
public class MeterDifferentiationType extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false )
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

    @ManyToOne()
    @JoinColumn(name = "accounting_point_Key_room_service_id", nullable = false)
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "differentiation_type_id", nullable = false)
    private DifferentiationType differentiationType;

}
