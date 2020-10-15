package com.best.billing.volumecalculator.models.enums;

import com.best.billing.volumecalculator.models.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Значение состояния прибора учета
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meter_states" )
public class MeterState extends BaseCatalog {
    public static final long ACTIVE_STATE_ID = 1;
    public static final long DISABLE_STATE_ID = 2;
    public static final long VERIFICATION_STATE_ID = 2;
}
