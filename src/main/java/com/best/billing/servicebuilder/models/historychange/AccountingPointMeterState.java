package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.convertors.MeterStateConvertor;
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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false )
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_id", nullable = false )
    private Meter meter;
    @JoinColumn(name = "meter_state_id", nullable = false )
    @Convert(converter = MeterStateConvertor.class)
    private MeterState meterState;
}
