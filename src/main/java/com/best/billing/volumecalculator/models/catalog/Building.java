package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Строение
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "buildings")
public class Building extends BaseCatalog {
}
