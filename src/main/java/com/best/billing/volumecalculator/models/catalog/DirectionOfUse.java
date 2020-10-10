package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Направление использования точек учета
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "direction_of_uses")
public class DirectionOfUse extends BaseCatalog {
}
