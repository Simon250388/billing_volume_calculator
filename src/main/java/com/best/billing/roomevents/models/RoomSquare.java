package com.best.billing.roomevents.models;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.convertors.SquareTypeConvertor;
import com.best.billing.roomevents.models.entity.KeyRoom;
import com.best.billing.common.model.enums.SquareType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История изменения площадей помещения
 */
@Data
@Builder
@Entity
@Table(name = "room_squares")
public class RoomSquare implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
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
