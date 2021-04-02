package com.best.billing.common.model;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;

import javax.persistence.*;

/**
 * Помещение
 */
@Data
@Builder
@Entity
@Table(name = "rooms")
public class Room implements BaseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
    /**
     * Родительское помещение, заполненно только для коммунальных квартир
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "parent_id")
    private Room parent;
}
