package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Точка учета
 */
@Entity
@Table(name = "accounting_points")
public class AccountingPoint extends BaseCatalog {
}
