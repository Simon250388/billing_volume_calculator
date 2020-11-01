package com.best.billing.servicebuilder.models.entity;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.Building;
import com.best.billing.common.model.Room;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Ключ помещения
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "key_rooms")
public class KeyRoom extends BaseEntity {
    /**
     * Строение
     */
    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
    /**
     * Помещение
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
    /**
     * Признак частного сектора
     */
    @Column(nullable = false)
    private boolean privateSector;
}
