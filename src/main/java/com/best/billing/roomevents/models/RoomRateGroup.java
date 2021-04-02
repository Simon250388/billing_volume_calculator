package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.RateGroup;
import com.best.billing.common.model.Service;
import com.best.billing.roomevents.models.entity.KeyRoom;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Тарифные группы услуг помещения
 */
@Data
@Entity
@Table(name = "room_rate_groups")
public class RoomRateGroup  implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rate_group_id", nullable = false)
    private RateGroup rateGroup;
}
