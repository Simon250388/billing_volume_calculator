package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Помещение
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rooms")
public class Room extends BaseCatalog {
    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
}
