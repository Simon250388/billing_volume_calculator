package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.KeyNorm;
import com.best.billing.common.model.Service;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Ключи нормативов для услуг помещения
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "room_service_key_norms")
public class RoomServiceKeyNorm implements BaseHistory {
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
    @JoinColumn(name = "key_norm_id", nullable = false)
    private KeyNorm keyNorm;
}
