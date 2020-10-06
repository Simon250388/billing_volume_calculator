package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.basemodels.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;

import javax.persistence.*;

/**
 * История изменения прописанных в помещении
 */
@Entity
@Table(name = "room_prescribers")
public class RoomPrescribed  extends BaseHistory {
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int prescribedCount;
}
