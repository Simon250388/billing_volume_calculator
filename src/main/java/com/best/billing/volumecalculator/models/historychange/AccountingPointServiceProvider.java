package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.catalog.Provider;
import com.best.billing.volumecalculator.models.catalog.Service;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Поставщик услуги на точке учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_point_service_provider")
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
    @ManyToOne()
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne
    @JoinColumn(name = "service_part_id", nullable = true)
    private Service servicePart;

    @ManyToOne()
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;
}
