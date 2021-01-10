package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.DifferentiationType;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История изменения вида диффиринцироованности
 * прибора учета на точке учета
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "meter_differentiation_type")
@NamedEntityGraph(
        name = "meter-differentiation-type-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(
                        value = "accountingPointKeyRoomServiceEntity",
                        subgraph = "accounting-point-Key-room-entity-graph" )
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

public class MeterDifferentiationType implements BaseHistory {
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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "differentiation_type_id", nullable = false)
    private DifferentiationType differentiationType;

}
