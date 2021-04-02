package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.AccountingPoint;
import com.best.billing.common.model.Provider;
import com.best.billing.common.model.Service;
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
 * Поставщик услуги на точке учета
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "accounting_point_service_provider")
@NamedEntityGraph(
        name = "accounting-point-service-provider-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("accountingPointKeyRoomServiceEntity")
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

public class AccountingPointServiceProvider implements BaseHistory, RoomEvent {
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
    @JoinColumn(name = "service_part_id")
    private Service servicePart;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @Override
    public RoomProperties register(RoomProperties origin) {
        RoomProperties result = origin.getCloneBuilder(this.period, this.periodFact).build();
        Map<AccountingPoint, AccountingPointProperties> accountingPointsPropertiesChange = new HashMap<>();
        result.getAccountingPointProperties().forEach((key, value) -> {
            if (key.equals(accountingPointKeyRoomServiceEntity.getAccountingPointKeyRoom().getAccountingPoint())) {
                AccountingPointProperties.AccountingPointPropertiesBuilder accountingPointPropertiesBuilder =
                        value.toBuilder()
                                .provider(provider);
                accountingPointsPropertiesChange.put(key, accountingPointPropertiesBuilder.build());
            }
        });

        accountingPointsPropertiesChange.forEach((key, value) -> result.getAccountingPointProperties().replace(key, value));

        return result;
    }
}
