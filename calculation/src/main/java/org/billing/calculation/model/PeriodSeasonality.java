package org.billing.calculation.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Период сезонности услуги. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "period_seasonality")
public class PeriodSeasonality {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private LocalDateTime period;

  @Column(nullable = false)
  private Integer year;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "seasonality_id", nullable = false)
  private Seasonality seasonality;
  /** Месяц начало действия сезонности. */
  @Column(nullable = false)
  private Integer monthStart;
  /** День месяца начала действия сезонности. */
  @Column(nullable = false)
  private Integer dayStart;
  /** Месяц окончания действия сезонности. */
  @Column(nullable = false)
  private Integer monthEnd;
  /** День месяца окончания действия сезонности. */
  @Column(nullable = false)
  private Integer dayEnd;
}
