package com.best.billingvolumecalculator.models.historychange;

import com.best.billingvolumecalculator.basemodels.BaseHistory;
import com.best.billingvolumecalculator.models.entity.KeyRoom;

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
