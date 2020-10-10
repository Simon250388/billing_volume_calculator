package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.entity.AccountingPointKeyRoomServiceEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Состояние услуги на точке учета
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_point_service_state")
public class AccountingPointServiceState extends BaseHistory {
    /**
     * Ключ услуги на точке учета
     */
    @ManyToOne
    @JoinColumn(name = "accounting_point_Key_room_service_id")
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    /**
     * Состояние услуги
     */
    @Column(name = "active", nullable = false)
    private boolean active;
}
