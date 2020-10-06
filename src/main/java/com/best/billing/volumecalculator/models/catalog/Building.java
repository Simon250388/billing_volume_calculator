package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Строение
 */
@Entity
@Table(name = "buildings")
public class Building extends BaseCatalog {
}
