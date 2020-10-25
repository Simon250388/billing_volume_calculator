package com.best.billing.servicebuilder.models.catalog;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Поставляемая услуга
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "services")
public class Service extends BaseCatalog {
}
