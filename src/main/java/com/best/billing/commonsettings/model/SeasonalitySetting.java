package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.Building;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Настройка применения сезонности
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "seasonality_settings")
public class SeasonalitySetting extends BaseHistory {
    @ManyToOne()
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne()
    @JoinColumn(name = "direction_of_use_Id", nullable = false)
    private DirectionOfUse directionOfUse;

    @ManyToOne()
    @JoinColumn(name = "building_id")
    private Building building;

    /**
     * Корректировать годовой объем
     */
    @Column(nullable = false)
    private Boolean correctAnnualVolume;
    /**
     * Начислять объем среднего за прошлый гож
     */
    @Column(nullable = false)
    private Boolean volumeByLastYear;
    /**
     * Коэффициент норматива
     */
    @Column(nullable = false)
    private Integer coefficientNormValue;

    /**
     * Коэффициент норматива когда сезонность не применяется
     */
    @Column(nullable = false)
    private Integer coefficientNormValueDoNotUseSeasonality;

    /**
     * Не применять настройки настройки сезонности
     */
    @Column(nullable = false)
    private Boolean doNotUseSeasonality;




}
