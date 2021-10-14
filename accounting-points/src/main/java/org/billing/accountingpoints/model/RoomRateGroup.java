package org.billing.accountingpoints.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Тарифные группы услуг помещения. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "room_rate_groups")
public class RoomRateGroup {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private LocalDateTime period;

  @Column(name = "periodFact")
  private LocalDateTime periodFact;

  @Column(name = "key_room_id", nullable = false)
  private UUID keyRoomId;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "service_id", nullable = false)
  private Service service;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "rate_group_id", nullable = false)
  private RateGroup rateGroup;
}
