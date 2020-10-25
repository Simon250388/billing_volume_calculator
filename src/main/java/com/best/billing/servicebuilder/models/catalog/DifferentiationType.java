package com.best.billing.servicebuilder.models.catalog;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Вид диффиринцированности
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "differentiation_types")
public class DifferentiationType extends BaseCatalog {
}
