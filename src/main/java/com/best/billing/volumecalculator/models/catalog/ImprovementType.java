package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Вид благоустройства помещения
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "improvement_types")
public class ImprovementType extends BaseCatalog {
}
