package com.best.billingvolumecalculator.models.catalog;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Точка учета
 */
@Entity
@Table(name = "accounting_points")
public class AccountingPoint extends BaseCatalog {
}
