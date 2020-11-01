package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Состояние услуги на точке учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_point_service_state")
@NamedQuery(
        name = AccountingPointServiceState.QUERY_FIND_ALL_LAST_ACTIVE_BY_KEY_ROOM_ID,
        query = " FROM AccountingPointServiceState c" +
                "   WHERE (c.accountingPointKeyRoomServiceEntity, c.period) IN (" +
                "       SELECT " +
                "       c.accountingPointKeyRoomServiceEntity" +
                "       ,MAX(c.period)" +
                "       FROM AccountingPointServiceState c" +
                "       WHERE c.accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id =:keyRoomId" +
                "       GROUP BY c.accountingPointKeyRoomServiceEntity)" +
                " AND c.active = true"
)
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
public class AccountingPointServiceState extends BaseHistory {
    public static final String QUERY_FIND_ALL_LAST_ACTIVE_BY_KEY_ROOM_ID = "AccountingPointServiceState.findAllActiveByKeyRoomId";

    /**
     * Ключ услуги на точке учета
     */
    @ManyToOne()
    @JoinColumn(name = "accounting_point_Key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    /**
     * Состояние услуги
     */
    @Column(name = "active", nullable = false)
    private boolean active;
}