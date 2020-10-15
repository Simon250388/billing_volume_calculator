package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * История изменения прописанных в помещении
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room_prescribers")
@NamedQuery(name = RoomPrescribed.FIND_ONE_LAST_BY_KEY_ROOM_ID,
        query = "FROM RoomPrescribed c" +
                " WHERE c.keyRoom = :keyRoomId" +
                " AND c.period = (  SELECT MAX(c.period)" +
                "                   FROM RoomPrescribed c" +
                "                   WHERE c.keyRoom =:keyRoomId)")
public class RoomPrescribed  extends BaseHistory {
    public static final String FIND_ONE_LAST_BY_KEY_ROOM_ID = "RoomPrescribed.findOneLastByKeyRoomId";
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int prescribedCount;
}
