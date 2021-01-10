package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История показаний прибора учета
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "meter_values")
@NamedEntityGraph(
        name = "meter-value-entity-graph",
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
public class MeterValue implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;
    @Column(nullable = false)
    private long value;
}
