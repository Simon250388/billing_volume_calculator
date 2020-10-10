package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Поставляемая услуга
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "services")
public class Service extends BaseCatalog {
}
