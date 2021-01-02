package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.Seasonality;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Период сезонности услуги
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "period_seasonality")
public class PeriodSeasonality extends BaseHistory {

    public static final String QUERY_FIND_ALL_BY_YEAR = "PeriodSeasonality.findAllByYear";
    @Column(nullable = false)
    private Integer year;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "seasonality_id", nullable = false)
    private Seasonality seasonality;
    /**
     * Месяц начало действия сезонности
     */
    @Column(nullable = false)
    private Integer monthStart;
    /**
     * День месяца начала действия сезонности
     */
    @Column(nullable = false)
    private Integer dayStart;
    /**
     * Месяц окончания действия сезонности
     */
    @Column(nullable = false)
    private Integer monthEnd;
    /**
     * День месяца окончания действия сезонности
     */
    @Column(nullable = false)
    private Integer dayEnd;
}
