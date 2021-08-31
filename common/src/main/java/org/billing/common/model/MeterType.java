package org.billing.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Тип прибора учета. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meter_types")
public class MeterType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version private long version;

  @Column(name = "description", nullable = false, length = 50)
  private String description;
}
