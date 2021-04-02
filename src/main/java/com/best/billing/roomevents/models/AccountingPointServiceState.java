package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.AccountingPoint;
import com.best.billing.departmen.customer.AccountingPointProperties;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.roomevents.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Состояние услуги на точке учета
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
    public RoomProperties register(RoomProperties origin) {
        RoomProperties result = origin.getCloneBuilder(this.period, this.periodFact).build();
        Map<AccountingPoint, AccountingPointProperties> accountingPointsPropertiesChange = new HashMap<>();
        result.getAccountingPointProperties().forEach((key, value) -> {
            if (key.equals(accountingPointKeyRoomServiceEntity.getAccountingPointKeyRoom().getAccountingPoint())) {
                AccountingPointProperties.AccountingPointPropertiesBuilder accountingPointPropertiesBuilder =
                        value.toBuilder().serviceActive(active);
                accountingPointsPropertiesChange.put(key, accountingPointPropertiesBuilder.build());
            }
        });

        accountingPointsPropertiesChange.forEach((key, value) -> result.getAccountingPointProperties().replace(key, value));

        return result;
    }
}
