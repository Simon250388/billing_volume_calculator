package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.convertors.MeterStateConvertor;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.common.model.enums.MeterState;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Состояние прибора учета на точке учета
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "accounting_point_meter_states")
public class AccountingPointMeterState implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
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
