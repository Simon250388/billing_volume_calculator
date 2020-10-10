package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * История изменения количества собственников
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room_owners")
public class RoomOwner extends BaseHistory {

    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int ownerCount;
}
