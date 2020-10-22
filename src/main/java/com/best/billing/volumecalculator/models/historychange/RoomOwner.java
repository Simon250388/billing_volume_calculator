package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * История изменения количества собственников
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "room_owners")
@NamedQuery(name = RoomOwner.FIND_ONE_LAST_BY_KEY_ROOM_ID,
        query = "FROM RoomOwner c" +
                " WHERE c.keyRoom.id = :keyRoomId" +
                " AND c.period = (  SELECT MAX(c.period)" +
                "                   FROM RoomOwner c" +
                "                   WHERE c.keyRoom.id =:keyRoomId)")
public class RoomOwner extends BaseHistory {
    public static final String FIND_ONE_LAST_BY_KEY_ROOM_ID = "RoomOwner.findOneLastByKeyRoomId";

    @ManyToOne
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    @Column(nullable = false)
    private int ownerCount;
}
