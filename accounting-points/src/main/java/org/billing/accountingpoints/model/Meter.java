package org.billing.accountingpoints.model;

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
import lombok.Data;
import lombok.NoArgsConstructor;
import org.billing.common.model.MeterType;

/** Прибор учета. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meters")
public class Meter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version private long version;

  @Column(name = "description", nullable = false, length = 50)
  private String description;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "meter_type_id", nullable = false)
  private MeterType meterType;
}
