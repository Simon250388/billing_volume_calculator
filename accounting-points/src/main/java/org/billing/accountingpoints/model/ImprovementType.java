package org.billing.accountingpoints.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Вид благоустройства помещения. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "improvement_types")
public class ImprovementType {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @Column(name = "description", nullable = false, length = 50)
  private String description;
}
