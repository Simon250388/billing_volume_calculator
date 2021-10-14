package org.billing.calculation.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Настройка расчета объема услуги по направлению использования. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "calculation_method_direction_of_use")
public class CalculationMethodByDirectionOfUse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private LocalDateTime period;

  @Column(name = "period_fact")
  private LocalDateTime periodFact;

  @Column(name = "service_id", nullable = false)
  private UUID serviceId;

  @Column(name = "direction_of_use_id", nullable = false)
  private UUID directionOfUseId;

  @Column(name = "square_type_id", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private SquareType squareType;
  /** Начислять норматив по дням. */
  @Column(nullable = false)
  private Boolean normByDay;
  /** Начислять за полный месяц в периодах изменения сезонности. */
  @Column(nullable = false)
  private Boolean calculateFullMonth;
}
