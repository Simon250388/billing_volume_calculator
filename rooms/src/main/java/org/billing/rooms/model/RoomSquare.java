package org.billing.rooms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История изменения площадей помещения
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "room_squares")
public class RoomSquare  {
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
    @Column(name = "squareType", nullable = false)
    @Enumerated(EnumType.STRING)
    private SquareType squareType;
    /**
     * Значение площади
     */
    @Column(nullable = false)
    private int value;
}
