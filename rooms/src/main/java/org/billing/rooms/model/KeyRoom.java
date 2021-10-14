package org.billing.rooms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Ключ помещения. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "key_rooms")
public class KeyRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version private long version;
  /** Строение. */
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "building_id", nullable = false)
  private Building building;
  /** Помещение. */
  @OneToOne(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "room_id")
  private Room room;
  /** Признак частного сектора. */
  @Column(name = "private_sector", nullable = false)
  private boolean privateSector;
}
