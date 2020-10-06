package com.best.billing.volumecalculator.models.enums;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Значение состояния прибора учета
 */
@Entity
@Table(name = "meter_states" )
public class MeterState extends BaseCatalog {
    static final long ACTIVE_STATE_ID = 1;
    static final long DISABLE_STATE_ID = 2;
    static final long VERIFICATION_STATE_ID = 2;
}
