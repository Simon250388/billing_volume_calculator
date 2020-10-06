package com.best.billing.volumecalculator.models.historychange;

import com.best.billing.volumecalculator.basemodels.BaseHistory;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import com.best.billing.volumecalculator.models.enums.SquareType;

import javax.persistence.*;

/**
 * История изменения площадей помещения
 */
@Entity
@Table(name = "room_squares")
public class RoomSquare extends BaseHistory {
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

    public KeyRoom getKeyRoom() {
        return keyRoom;
    }

    public void setKeyRoom(KeyRoom keyRoom) {
        this.keyRoom = keyRoom;
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public void setSquareType(SquareType squareType) {
        this.squareType = squareType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
