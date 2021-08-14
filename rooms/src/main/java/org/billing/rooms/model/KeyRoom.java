package org.billing.rooms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.billing.common.model.Building;
import org.billing.common.model.Room;

import javax.persistence.*;


/**
 * Ключ помещения
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "key_rooms")
public class KeyRoom {
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
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_id")
    private Room room;
    /**
     * Признак частного сектора
     */
    @Column(name = "private_sector", nullable = false)
    private boolean privateSector;
}

