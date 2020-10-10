package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Строение
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "buildings")
public class Building extends BaseCatalog {
}
