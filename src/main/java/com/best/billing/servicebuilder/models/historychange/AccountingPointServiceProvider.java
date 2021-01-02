package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.Provider;
import com.best.billing.common.model.Service;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Поставщик услуги на точке учета
 */
@SuppressWarnings("ALL")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
@NamedQuery(name = AccountingPointServiceProvider.FIND_ALL_LAST_BY_KEY_ROOM_ID,
        query = " FROM AccountingPointServiceProvider" +
                " WHERE (accountingPointKeyRoomServiceEntity, servicePart, period)  IN (" +
                "       SELECT " +
                "           point_provider.accountingPointKeyRoomServiceEntity" +
                "           ,point_provider.servicePart" +
                "           ,MAX(point_provider.period)" +
                "       FROM AccountingPointServiceProvider point_provider" +
                "       WHERE point_provider.accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id =:keyRoomId" +
                "       GROUP BY" +
                "           point_provider.accountingPointKeyRoomServiceEntity" +
                "           ,point_provider.servicePart)"
)
public class AccountingPointServiceProvider extends BaseHistory {
    public static final String FIND_ALL_LAST_BY_KEY_ROOM_ID = "AccountingPointServiceProvider.findAllLastByKeyRoomId";
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_part_id")
    private Service servicePart;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;
}
