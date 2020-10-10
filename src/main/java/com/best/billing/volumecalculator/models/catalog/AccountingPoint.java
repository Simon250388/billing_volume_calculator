package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Точка учета
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounting_points")
public class AccountingPoint extends BaseCatalog {
}
