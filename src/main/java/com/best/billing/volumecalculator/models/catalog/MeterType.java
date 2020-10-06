package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тип прибора учета
 */
@Entity
@Table(name = "meter_types")
public class MeterType extends BaseCatalog {
}
