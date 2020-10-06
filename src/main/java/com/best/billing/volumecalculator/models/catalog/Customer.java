package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Контрагент
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseCatalog {
}
