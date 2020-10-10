package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тип прибора учета
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meter_types")
public class MeterType extends BaseCatalog {
}
