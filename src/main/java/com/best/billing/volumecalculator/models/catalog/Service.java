package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Поставляемая услуга
 */
@Entity
@Table(name = "services")
public class Service extends BaseCatalog {
}
