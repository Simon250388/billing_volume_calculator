package com.best.billing.common.model;

import com.best.billing.base.model.BaseCatalog;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Помещение
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rooms")
public class Room extends BaseCatalog {
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
