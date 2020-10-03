package com.best.billingvolumecalculator.models.catalog;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Помещение
 */
@Entity
@Table(name = "rooms")
public class Room extends BaseCatalog {
    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
}
