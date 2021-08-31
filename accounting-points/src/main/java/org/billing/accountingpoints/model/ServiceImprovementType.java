package org.billing.accountingpoints.model;

import java.time.LocalDateTime;
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
import org.billing.common.model.ImprovementType;
import org.billing.common.model.Service;
import org.billing.rooms.model.KeyRoom;

/** Вид благоустройства для услуг помещения. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "room_improvement_types")
public class ServiceImprovementType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version private long version;

  @Column(name = "period", nullable = false)
  private LocalDateTime period;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "key_room_id", nullable = false)
  private KeyRoom keyRoom;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "service_id", nullable = false)
  private Service service;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "improvement_type_id", nullable = false)
  private ImprovementType improvementType;
}
