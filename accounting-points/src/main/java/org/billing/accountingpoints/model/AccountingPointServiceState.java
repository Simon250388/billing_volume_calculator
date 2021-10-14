package org.billing.accountingpoints.model;

import java.time.Instant;
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
import lombok.Data;
import lombok.NoArgsConstructor;

/** Состояние услуги на точке учета. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounting_point_service_state")
public class AccountingPointServiceState {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private Instant period;

  @Column(name = "period_fact")
  private Instant periodFact;
  /** Ключ услуги на точке учета. */
  @ManyToOne(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
  private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
  /** Состояние услуги. */
  @Column(name = "active", nullable = false)
  private boolean active;
}
