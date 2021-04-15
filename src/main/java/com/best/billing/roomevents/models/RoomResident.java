package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.KeyRoom;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История изменения проживающих
 */
@Data
@Builder
@Entity
@Table(name = "room_residents")
public class RoomResident implements BaseHistory, RoomEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @Column(name = "period_fact")
    private LocalDateTime periodFact;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int residentCount;

    @Override
    public RoomProperties register(@NonNull final RoomProperties origin, final RoomEvent previousEvent) {
        return origin.getNewInstance(this, previousEvent)
                .roomResident(residentCount)
                .build();
    }
}
