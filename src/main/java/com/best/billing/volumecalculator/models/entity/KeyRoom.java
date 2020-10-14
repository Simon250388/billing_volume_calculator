package com.best.billing.volumecalculator.models.entity;

import com.best.billing.volumecalculator.models.BaseEntity;
import com.best.billing.volumecalculator.models.catalog.Building;
import com.best.billing.volumecalculator.models.catalog.Room;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Ключ помещения
 */
@Data
@SuperBuilder
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
    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
    /**
     * Признак частного сектора
     */
    @Column(nullable = false)
    private boolean privateSector;
}
