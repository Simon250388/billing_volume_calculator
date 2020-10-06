package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Направление использования точек учета
 */
@Entity
@Table(name = "direction_of_uses")
public class DirectionOfUse extends BaseCatalog {
}
