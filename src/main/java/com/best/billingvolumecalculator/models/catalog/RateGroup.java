package com.best.billingvolumecalculator.models.catalog;

import com.best.billingvolumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тарифная группа
 */
@Entity
@Table(name = "rate_groups")
public class RateGroup extends BaseCatalog {
}
