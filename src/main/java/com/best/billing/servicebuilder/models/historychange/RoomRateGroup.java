package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.RateGroup;
import com.best.billing.common.model.Service;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
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
