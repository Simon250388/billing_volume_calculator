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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Поставщик услуги на точке учета. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounting_point_service_provider")
public class AccountingPointServiceProvider {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private Long version;

  @Column(name = "period", nullable = false)
  private Instant period;

  @Column(name = "period_fact")
  private Instant periodFact;

  @ManyToOne(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
  private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

  @ManyToOne(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "service_part_id")
  private Service servicePart;

  @ManyToOne(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id", nullable = false)
  private Provider provider;
}
