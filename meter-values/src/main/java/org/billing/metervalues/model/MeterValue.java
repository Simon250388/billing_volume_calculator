package org.billing.metervalues.model;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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

/** История показаний прибора учета. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meter_values")
public class MeterValue {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private Instant period;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "meter_id", nullable = false)
  private Meter meter;

  @Column(nullable = false)
  private double value;
  /** Тип события которым были зарегистрированы показания. */
  @Column(name = "event_type_id", nullable = false)
  @Convert(converter = MethodProvideMeterValueConvertor.class)
  private MethodProvideMeterValue methodProvideMeterValue;
  /** Ключ события которым были зарегестрированы показания. */
  @Column(name = "event_id")
  private long eventId;
}
