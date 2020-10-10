package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Прибор учета
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meters")
public class Meter extends BaseCatalog {
    @ManyToOne
    @JoinColumn(name = "meter_type_id")
    private MeterType meterType;
}
