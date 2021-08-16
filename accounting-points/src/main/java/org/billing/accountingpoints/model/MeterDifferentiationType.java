package org.billing.accountingpoints.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import org.billing.common.model.DifferentiationType;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private LocalDateTime period;

  @Column(name = "period_fact")
  private LocalDateTime periodFact;

  @ManyToOne(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
  private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

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
    MeterDifferentiationType book = (MeterDifferentiationType) obj;
    return Objects.equals(getId(), book.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        period, periodFact, accountingPointKeyRoomServiceEntity.getId(), meter.getId());
  }
}
