package com.best.billing.servicebuilder.models.historychange;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.convertors.SquareTypeConvertor;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.common.model.enums.SquareType;
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
public class RoomSquare extends BaseHistory {
    public static final String FIND_ONE_LAST_COMMON_SQUARE_BY_KEY_ROOM_ID = "RoomSquare.findOneLastCommonSquareByKeyRoomId";
    /**
     * Ключ помещения
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;
    /**
     * Тип площади
     */
    @JoinColumn(nullable = false)
    @Convert(converter = SquareTypeConvertor.class)
    private SquareType squareType;
    /**
     * Значение площади
     */
    @Column(nullable = false)
    private int value;
}
