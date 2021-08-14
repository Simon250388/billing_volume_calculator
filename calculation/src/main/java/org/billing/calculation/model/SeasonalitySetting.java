package org.billing.calculation.model;

import lombok.*;
import org.billing.common.model.Building;
import org.billing.common.model.DirectionOfUse;
import org.billing.common.model.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Настройка применения сезонности
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seasonality_settings")
public class SeasonalitySetting {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SeasonalitySetting)) return false;
        SeasonalitySetting castObj = (SeasonalitySetting) obj;
        return Objects.equals(getId(), castObj.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                period,
                service.getId(),
                directionOfUse.getId(),
                building.getId()
        );
    }

}
