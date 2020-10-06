package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тарифная группа
 */
@Entity
@Table(name = "rate_groups")
public class RateGroup extends BaseCatalog {
}
