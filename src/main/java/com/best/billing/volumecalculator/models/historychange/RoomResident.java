package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * История изменения проживающих
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room_residents")
@NamedQuery(name = RoomResident.FIND_ONE_LAST_BY_KEY_ROOM_ID,  query = " FROM RoomResident c" +
        " WHERE c.keyRoom.id = :keyRoomId" +
        " AND c.period = (SELECT MAX(c.period)" +
        "                 FROM RoomResident c" +
        "                 WHERE c.keyRoom.id =:keyRoomId)")
public class RoomResident extends BaseHistory {
    public static final String FIND_ONE_LAST_BY_KEY_ROOM_ID = "RoomResident.findOneLastByKeyRoomId";
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int residentCount;
}
