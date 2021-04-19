package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.metervalues.model.MeterValue;
import com.best.billing.metervalues.model.MethodProvideMeterValue;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Состояние услуги на точке учета
 */
@Data
@Builder
@Entity
@Table(name = "accounting_point_service_state")
@NamedEntityGraph(
        name = "accounting-point-service-state-key-room-graph",
        attributeNodes = {
                @NamedAttributeNode(
                        value = "accountingPointKeyRoomServiceEntity",
                        subgraph = "accounting-pointKey-room-entity-graph"),

        },
        subgraphs = {
                @NamedSubgraph(
                        name = "accounting-pointKey-room-entity-graph",
                        attributeNodes = {
                                @NamedAttributeNode("accountingPointKeyRoom")
                        }
                )
        }

)
public class AccountingPointServiceState implements BaseHistory, RoomEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @Column(name = "period_fact")
    private LocalDateTime periodFact;
    /**
     * Ключ услуги на точке учета
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_Key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    /**
     * Состояние услуги
     */
    @Column(name = "active", nullable = false)
    private boolean active;

    @Override
    public RoomProperties register(@NonNull final RoomProperties previousProperties, final RoomEvent previousEvent, @NonNull final List<MeterValue> currentMeterValues) {

        RoomProperties result = previousProperties
                .cloneBuilder(previousEvent, currentMeterValues)
                .registrationPeriod(period)
                .registrationPeriodFact(periodFact)
                .build();

        for (int i=0;i<result.getAccountingPointProperties().size();i++) {
            AccountingPointProperty accountingPointProperty = result.getAccountingPointProperties().get(i);
            if (accountingPointProperty.getAccountingPointId() == this.accountingPointKeyRoomServiceEntity.getAccountingPointKeyRoom().getAccountingPoint().getId()) {
                AccountingPointProperty.AccountingPointPropertyBuilder accountingPointPropertiesBuilder =
                        accountingPointProperty.toBuilder().serviceActive(this.active);
                result.getAccountingPointProperties().set(i,accountingPointPropertiesBuilder.build());
            }
        }
        return result;
    }

    @Override
    public boolean isProvideMeterValue() {
        return true;
    }

    @Override
    public MethodProvideMeterValue getMethodProvideMeterValue() {
        return MethodProvideMeterValue.ACCOUNTING_POINT_SERVICE_PROVIDER;
    }
}
