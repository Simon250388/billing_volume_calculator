package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.RateGroup;
import com.best.billing.common.model.Service;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Значения тарифов услуг
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rate_values")
public class RateValue extends BaseHistory {
    public static final String QUERY_FIND_ALL_LAST_BY_PERIOD = "RateValue.findAllLAstByPeriod";
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rate_group_id", nullable = false)
    private RateGroup rateGroup;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    /**
     * Значение тарифа
     */
    @Column(nullable = false)
    private Integer value;
}
