package org.billing.metervalues.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** История изменения вида диффиринцироованности прибора учета на точке учета. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meter_differentiation_type")
public class MeterDifferentiationType {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private Instant period;

  @Column(name = "period_fact")
  private Instant periodFact;

  @ManyToOne(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "meter_id", nullable = false)
  private Meter meter;

  @ManyToOne(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "differentiation_type_id", nullable = false)
  private DifferentiationType differentiationType;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MeterDifferentiationType)) {
      return false;
    }
    MeterDifferentiationType that = (MeterDifferentiationType) obj;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        period, periodFact, meter.getId());
  }
}
