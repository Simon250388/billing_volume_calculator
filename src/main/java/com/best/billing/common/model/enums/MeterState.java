package com.best.billing.common.model.enums;

import com.best.billing.base.model.BaseCatalog;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Immutable;

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
@Immutable
public class MeterState extends BaseCatalog {
    public static final long ACTIVE_STATE_ID = 1;
    public static final MeterState ACTIVE_STATE = MeterState.builder().id(MeterState.ACTIVE_STATE_ID).build();
    public static final long DISABLE_STATE_ID = 2;
    public static final long VERIFICATION_STATE_ID = 2;
}
