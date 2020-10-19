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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class AccountingPointServiceProvider extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "accounting_point_key_room_service_entity_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

    @ManyToOne
    @JoinColumn(name = "service_Id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "provider_Id", nullable = false)
    private Provider provider;
}
