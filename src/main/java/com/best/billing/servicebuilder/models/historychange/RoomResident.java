package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История изменения проживающих
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "room_residents")
public class RoomResident implements BaseHistory {
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
    @Column(nullable = false)
    private int residentCount;
}
