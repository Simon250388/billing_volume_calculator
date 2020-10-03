package com.best.billingvolumecalculator.models.catalog;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Вид благоустройства помещения
 */
@Entity
@Table(name = "improvement_types")
public class ImprovementType extends BaseCatalog {
}
