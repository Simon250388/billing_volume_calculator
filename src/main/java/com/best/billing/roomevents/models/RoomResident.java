package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.KeyRoom;
import com.best.billing.departmen.customer.RoomEvent;
import com.best.billing.departmen.customer.RoomProperties;
import com.best.billing.metervalues.model.MeterValue;
import com.best.billing.metervalues.model.MethodProvideMeterValue;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private short residentCount;

    @Override
    public RoomProperties register(@NonNull final RoomProperties previousProperties, final RoomEvent previousEvent, @NonNull final List<MeterValue> currentMeterValues) {
        return previousProperties.cloneBuilder(previousEvent, currentMeterValues)
                .registrationPeriod(period)
                .registrationPeriodFact(periodFact)
                .roomResident(residentCount)
                .build();
    }

    @Override
    public boolean isProvideMeterValue() {
        return false;
    }

    @Override
    public MethodProvideMeterValue getMethodProvideMeterValue() {
        return null;
    }
}
