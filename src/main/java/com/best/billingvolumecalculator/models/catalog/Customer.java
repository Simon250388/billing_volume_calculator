package com.best.billingvolumecalculator.models.catalog;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Контрагент
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseCatalog {
}
