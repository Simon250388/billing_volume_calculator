package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
public class AccountingPointServiceState implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
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
}
