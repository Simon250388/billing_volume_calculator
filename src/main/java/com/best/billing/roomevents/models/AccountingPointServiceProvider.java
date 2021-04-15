package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.Provider;
import com.best.billing.common.model.Service;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Поставщик услуги на точке учета
 */
@Data
@Builder
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
    public RoomProperties register(@NonNull final RoomProperties origin, final RoomEvent previousEvent) {
        RoomProperties result = origin.getNewInstance(this, previousEvent).build();
        result.getAccountingPointProperties().forEach(accountingPointProperty -> {
            for (int i = 0; i< accountingPointProperty.getServicePartProperties().size(); i++) {
                ServicePartProperty servicePartProperty = accountingPointProperty.getServicePartProperties().get(i);
                if (servicePartProperty.getServicePartId() == this.servicePart.getId()) {
                    final ServicePartProperty newServicePartProperty = servicePartProperty.toBuilder().providerId(this.provider.getId()).build();
                    accountingPointProperty.getServicePartProperties().set(i, newServicePartProperty);
                }
            }
        });
        return result;
    }
}
