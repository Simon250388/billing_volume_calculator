package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Вид благоустройства помещения
 */
@Entity
@Table(name = "improvement_types")
public class ImprovementType extends BaseCatalog {
}
