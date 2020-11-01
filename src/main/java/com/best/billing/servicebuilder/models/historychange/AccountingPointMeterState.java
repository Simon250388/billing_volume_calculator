package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.common.model.enums.MeterState;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Состояние прибора учета на точке учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_point_meter_states")
public class AccountingPointMeterState extends BaseHistory {
    @ManyToOne()
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false )
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne
    @JoinColumn(name = "meter_id", nullable = false )
    private Meter meter;
    @ManyToOne
    @JoinColumn(name = "meter_state_id", nullable = false )
    private MeterState meterState;
}
