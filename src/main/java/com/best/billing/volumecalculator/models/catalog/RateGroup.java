package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тарифная группа
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rate_groups")
public class RateGroup extends BaseCatalog {
}
