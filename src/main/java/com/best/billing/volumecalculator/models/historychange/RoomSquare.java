package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.models.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.models.enums.SquareType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * История изменения площадей помещения
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room_squares")
@NamedQuery(name = RoomSquare.FIND_ONE_LAST_COMMON_SQUARE_BY_KEY_ROOM_ID,
        query = "FROM RoomSquare c" +
                "         WHERE c.keyRoom =:keyRoomId" +
                "         AND c.squareType.id =" + SquareType.COMMON_SQUARE_TYPE_ID + "" +
                "         AND c.period = (SELECT MAX(c.period)" +
                "                         FROM RoomSquare c" +
                "                         WHERE c.keyRoom.id =:keyRoomId)" +
                "                         AND c.squareType.id =" + SquareType.COMMON_SQUARE_TYPE_ID + "")
public class RoomSquare extends BaseHistory {
    public static final String FIND_ONE_LAST_COMMON_SQUARE_BY_KEY_ROOM_ID = "RoomSquare.findOneLastCommonSquareByKeyRoomId";
    /**
     * Ключ помещения
     */
    @ManyToOne
    @JoinColumn(name = "key_room_id")
    private KeyRoom keyRoom;
    /**
     * Тип площади
     */
    @ManyToOne
    @JoinColumn(name = "square_type_id")
    private SquareType squareType;
    /**
     * Значение площади
     */
    @Column(nullable = false)
    private int value;
}
