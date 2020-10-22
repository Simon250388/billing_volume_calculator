package com.best.billing.volumecalculator.models.catalog;


import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Поставщик услуги
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "providers")
public class Provider extends BaseCatalog {

}
