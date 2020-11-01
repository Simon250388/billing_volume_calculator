package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
