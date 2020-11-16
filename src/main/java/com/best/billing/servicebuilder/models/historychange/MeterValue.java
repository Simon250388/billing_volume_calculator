package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * История показаний прибора учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
@NamedQuery(
        name = MeterValue.FIND_ALL_LAST_BY_KEY_ROOM_ID,
        query = "   FROM MeterValue mv" +
                "   WHERE (mv.accountingPointKeyRoomServiceEntity, mv.meter, mv.period) IN " +
                "       (SELECT " +
                "       mv.accountingPointKeyRoomServiceEntity, mv.meter, MAX(mv.period)" +
                "       FROM MeterValue mv" +
                "       WHERE mv.accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id =:keyRoomId" +
                "       GROUP BY mv.accountingPointKeyRoomServiceEntity, mv.meter)")
public class MeterValue extends BaseHistory {
    public static final String FIND_ALL_LAST_BY_KEY_ROOM_ID = "MeterValue.findAllLastByKeyRoomId";
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;
    @Column(nullable = false)
    private long value;
}
