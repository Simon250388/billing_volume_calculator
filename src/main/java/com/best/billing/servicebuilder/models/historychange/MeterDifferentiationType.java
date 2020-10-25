package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.catalog.DifferentiationType;
import com.best.billing.servicebuilder.models.catalog.Meter;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * История изменения вида диффиринцироованности
 * прибора учета на точке учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meter_differentiation_type")
@NamedQuery(
        name = MeterDifferentiationType.FIND_ALL_LAST_BY_KEY_ROOM_ID,
        query =" FROM MeterDifferentiationType difType" +
                " WHERE (difType.accountingPointKeyRoomServiceEntity, difType.meter, difType.period) IN (" +
                "       SELECT difType.accountingPointKeyRoomServiceEntity, difType.meter, MAX(difType.period)" +
                "       FROM MeterDifferentiationType difType" +
                "       WHERE difType.accountingPointKeyRoomServiceEntity.accountingPointKeyRoom.keyRoom.id =:keyRoomId" +
                "       GROUP BY difType.accountingPointKeyRoomServiceEntity, difType.meter)")
public class MeterDifferentiationType extends BaseHistory {
    public static final String FIND_ALL_LAST_BY_KEY_ROOM_ID = "MeterDifferentiationType.findAllLastByKeyRoomId";
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

    @ManyToOne()
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "differentiation_type_id", nullable = false)
    private DifferentiationType differentiationType;

}
