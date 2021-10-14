package org.billing.accountingpoints.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

/** Состояние прибора учета на точке учета. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounting_point_meter_states")
public class AccountingPointMeterState {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private LocalDateTime period;

  @Column(name = "period_fact")
  private LocalDateTime periodFact;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
  private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

  @Column(name = "meter_id", nullable = false)
  private UUID meterId;

  @Column(name = "meter_state_id", nullable = false)
  @Enumerated(EnumType.STRING)
  private MeterState meterState;

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AccountingPointMeterState other = (AccountingPointMeterState) obj;
    return Objects.equals(id, other.getId());
  }
}
