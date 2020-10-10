package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тарифная группа
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rate_groups")
public class RateGroup extends BaseCatalog {
}
