package org.billing.accountingpoints.model;

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

/** Точка учета ключа помещения. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounting_point_key_room")
public class AccountingPointKeyRoom {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;
  /** Ключ помещения. */
  @Column(name = "key_room_id", nullable = false, length = 36)
  private UUID keyRoomId;
  /** Точка учета. */
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "accounting_point_id", nullable = false)
  private AccountingPoint accountingPoint;
}
