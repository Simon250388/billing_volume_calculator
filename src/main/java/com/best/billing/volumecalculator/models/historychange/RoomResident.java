package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * История изменения проживающих
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room_residents")
public class RoomResident extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int residentCount;
}
