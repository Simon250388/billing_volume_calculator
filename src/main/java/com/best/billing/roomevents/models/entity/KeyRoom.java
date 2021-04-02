package com.best.billing.roomevents.models.entity;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.Building;
import com.best.billing.common.model.Room;
import lombok.*;

import javax.persistence.*;

/**
 * Ключ помещения
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "key_rooms")
public class KeyRoom implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    /**
     * Строение
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
    /**
     * Помещение
     */
    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_id")
    private Room room;
    /**
     * Признак частного сектора
     */
    @Column(nullable = false)
    private boolean privateSector;
}
