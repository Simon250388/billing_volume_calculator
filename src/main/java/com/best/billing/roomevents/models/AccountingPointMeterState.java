package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.convertors.MeterStateConvertor;
import com.best.billing.common.model.enums.MeterState;
import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.common.model.Meter;
import com.best.billing.departmen.customer.RoomProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Состояние прибора учета на точке учета
 */
@Data
@Builder
@Entity
@Table(name = "accounting_point_meter_states")
public class AccountingPointMeterState implements BaseHistory, RoomEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @Column(name = "period_fact")
    private LocalDateTime periodFact;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;
    @JoinColumn(name = "meter_state_id", nullable = false)
    @Convert(converter = MeterStateConvertor.class)
    private MeterState meterState;

    @Override
    public RoomProperties register(RoomProperties origin) {
        RoomProperties result = origin.getNewInstance(this.period, this.periodFact).build();
        for (int i=0;i<result.getAccountingPointProperties().size();i++) {
            AccountingPointProperty accountingPointProperty = result.getAccountingPointProperties().get(i);
            if (accountingPointProperty.getMeterId() == this.meter.getId()) {
                AccountingPointProperty.AccountingPointPropertyBuilder accountingPointPropertiesBuilder =
                        accountingPointProperty.toBuilder().meterState(this.meterState);
                result.getAccountingPointProperties().set(i,accountingPointPropertiesBuilder.build());
            }
        }
        return result;
    }
}
