package org.billing.calculation.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Настройка применения сезонности. */
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

  @Version private long version;

  @Column(name = "period", nullable = false)
  private LocalDateTime period;

  @Column(name = "service_id", nullable = false)
  private UUID serviceId;

  @Column(name = "direction_of_use_id", nullable = false)
  private UUID directionOfUseId;

  @Column(name = "building_id", length = 36)
  private String building;
  /** Корректировать годовой объем. */
  @Column(nullable = false)
  private boolean correctAnnualVolume;
  /** Начислять объем среднего за прошлый год. */
  @Column(nullable = false)
  private boolean volumeByLastYear;
  /** Коэффициент норматива. */
  @Column(nullable = false)
  private int coefficientNormValue;
  /** Коэффициент норматива когда сезонность не применяется. */
  @Column(nullable = false)
  private int coefficientNormValueDoNotUseSeasonality;
  /** Не применять настройки настройки сезонности. */
  @Column(nullable = false)
  private boolean doNotUseSeasonality;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof SeasonalitySetting)) {
      return false;
    }
    SeasonalitySetting castObj = (SeasonalitySetting) obj;
    return Objects.equals(getId(), castObj.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(period, serviceId, directionOfUseId, building);
  }
}
