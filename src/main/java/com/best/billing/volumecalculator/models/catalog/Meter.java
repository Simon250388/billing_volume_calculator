package com.best.billing.volumecalculator.models.catalog;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Прибор учета
 */
@Entity
@Table(name = "meters")
public class Meter extends BaseCatalog {
    @ManyToOne
    @JoinColumn(name = "meter_type_id")
    private MeterType meterType;

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
    }
}
