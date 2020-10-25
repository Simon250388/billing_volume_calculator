package com.best.billing.servicebuilder.models.catalog;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тип прибора учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meter_types")
public class MeterType extends BaseCatalog {
}
