package com.best.billing.servicebuilder.models.catalog;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Контрагент
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customers")
public class Customer extends BaseCatalog {
}
