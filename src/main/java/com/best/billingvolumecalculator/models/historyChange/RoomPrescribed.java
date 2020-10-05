package com.best.billingvolumecalculator.models.historyChange;

import com.best.billingvolumecalculator.basemodels.BaseHistoty;
import com.best.billingvolumecalculator.models.entity.KeyRoom;

import javax.persistence.*;

/**
 * История изменения прописанных в помещении
 */
@Entity
@Table(name = "room_prescribers")
public class RoomPrescribed  extends BaseHistoty {
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int prescribedCount;
}
