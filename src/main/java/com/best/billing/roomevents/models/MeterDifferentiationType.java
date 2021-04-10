package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.DifferentiationType;
import com.best.billing.common.model.Meter;
import com.best.billing.departmen.customer.AccountingPointProperty;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История изменения вида диффиринцироованности
 * прибора учета на точке учета
 */
@Data
@Builder
@Entity
@Table(name = "meter_differentiation_type")
@NamedEntityGraph(
        name = "meter-differentiation-type-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(
                        value = "accountingPointKeyRoomServiceEntity",
                        subgraph = "accounting-point-Key-room-entity-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "accounting-point-Key-room-entity-graph",
                        attributeNodes = {
                                @NamedAttributeNode("accountingPointKeyRoom")
                        }
                )
        }
)

public class MeterDifferentiationType implements BaseHistory, RoomEvent {
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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "differentiation_type_id", nullable = false)
    private DifferentiationType differentiationType;
    @Override
    public RoomProperties register(RoomProperties origin) {
        RoomProperties result = origin.getNewInstance(this.period, this.periodFact).build();

        for (int i=0;i<result.getAccountingPointProperties().size();i++) {
            AccountingPointProperty accountingPointProperty = result.getAccountingPointProperties().get(i);
            if (accountingPointProperty.getMeterId() == this.meter.getId()) {
                AccountingPointProperty.AccountingPointPropertyBuilder accountingPointPropertiesBuilder =
                        accountingPointProperty.toBuilder().differentiationTypeId(this.differentiationType.getId());
                result.getAccountingPointProperties().set(i,accountingPointPropertiesBuilder.build());
            }
        }

        return result;
    }
}
