package com.best.billing.common.model;

import com.best.billing.base.model.BaseCatalog;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    /**
     * Родительское помещение, заполненно только для коммунальных квартир
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Room parent;
}
