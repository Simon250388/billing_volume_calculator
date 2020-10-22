package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.catalog.RateGroup;
import com.best.billing.volumecalculator.models.catalog.Service;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Тарифные группы услуг помещения
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room_rate_groups")
public class RoomRateGroup  extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne
    @JoinColumn(name = "rate_group_id", nullable = false)
    private RateGroup rateGroup;
}
