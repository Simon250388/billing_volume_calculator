package com.best.billingvolumecalculator.models.catalog;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Строение
 */
@Entity
@Table(name = "buildings")
public class Building extends BaseCatalog {
}
