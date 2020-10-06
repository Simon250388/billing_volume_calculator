package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Вид диффиринцированности
 */
@Entity
@Table(name = "differentiation_types")
public class DifferentiationType extends BaseCatalog {
}
