package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.KeyNorm;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Значение норматива потребления
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rate_values")
public class KeyNormValue extends BaseHistory {
    public static final String QUERY_FIND_ALL_LAST_BY_PERIOD = "KeyNormValue.findAllLastByPeriod";
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "key_norm_Id", nullable = false)
    private KeyNorm keyNorm;
    @Column(nullable = false)
    private Integer normValue;
}
