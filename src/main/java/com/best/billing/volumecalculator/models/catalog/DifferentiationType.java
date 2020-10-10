package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Вид диффиринцированности
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "differentiation_types")
public class DifferentiationType extends BaseCatalog {
}
