package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.KeyRoom;
import com.best.billing.common.model.RateGroup;
import com.best.billing.common.model.Service;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.departmen.customer.ServicePartProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Тарифные группы услуг помещения
 */
@Data
@Builder
@Entity
@Table(name = "room_rate_groups")
public class RoomRateGroup  implements BaseHistory, RoomEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @Column(name = "periodFact")
    private LocalDateTime periodFact;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rate_group_id", nullable = false)
    private RateGroup rateGroup;

    @Override
    public RoomProperties register(@NonNull final RoomProperties origin) {
        RoomProperties result = origin.getNewInstance(this.period, this.periodFact).build();
        result.getAccountingPointProperties().forEach(accountingPointProperty -> {
            for (int i = 0; i< accountingPointProperty.getServicePartProperties().size(); i++) {
                ServicePartProperty servicePartProperty = accountingPointProperty.getServicePartProperties().get(i);
                if (servicePartProperty.getServicePartId() == this.service.getId()) {
                    final ServicePartProperty newServicePartProperty = servicePartProperty.toBuilder().roomRateGroupId(this.rateGroup.getId()).build();
                    accountingPointProperty.getServicePartProperties().set(i, newServicePartProperty);
                }
            }
        });

        return result;
    }
}
