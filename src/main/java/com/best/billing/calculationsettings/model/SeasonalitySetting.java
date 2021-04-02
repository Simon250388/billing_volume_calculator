package com.best.billing.calculationsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.Building;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Настройка применения сезонности
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "seasonality_settings")
public class SeasonalitySetting implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "direction_of_use_Id", nullable = false)
    private DirectionOfUse directionOfUse;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "building_id")
    private Building building;
    /**
     * Корректировать годовой объем
     */
    @Column(nullable = false)
    private boolean correctAnnualVolume;
    /**
     * Начислять объем среднего за прошлый гож
     */
    @Column(nullable = false)
    private boolean volumeByLastYear;
    /**
     * Коэффициент норматива
     */
    @Column(nullable = false)
    private int coefficientNormValue;
    /**
     * Коэффициент норматива когда сезонность не применяется
     */
    @Column(nullable = false)
    private int coefficientNormValueDoNotUseSeasonality;
    /**
     * Не применять настройки настройки сезонности
     */
    @Column(nullable = false)
    private boolean doNotUseSeasonality;
}
