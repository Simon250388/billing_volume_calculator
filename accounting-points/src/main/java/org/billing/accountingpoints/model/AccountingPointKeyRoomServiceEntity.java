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

/** Услуга точки учета (без учета состояния). */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounting_point_key_room_service_entity")
public class AccountingPointKeyRoomServiceEntity {

  public static final String SELECT_ID_WHERE_KEY_ROOM_ID = "" +
          "SELECT APS.ID " +
          "FROM ACCOUNTING_POINT_KEY_ROOM_SERVICE_ENTITY APS " +
          "INNER JOIN ACCOUNTING_POINT_KEY_ROOM AP " +
          "ON APS.ACCOUNTING_POINT_KEY_ROOM_ID = AP.ID " +
          "WHERE AP.KEY_ROOM_ID = :keyRoomId";

  public static final String SELECT_ID_WHERE_KEY_ROOM_ID_AND_ACC_POINT_ID = "" +
          "SELECT APS.ID " +
          "FROM ACCOUNTING_POINT_KEY_ROOM_SERVICE_ENTITY APS " +
          "INNER JOIN ACCOUNTING_POINT_KEY_ROOM AP " +
          "ON APS.ACCOUNTING_POINT_KEY_ROOM_ID = AP.ID " +
          "WHERE AP.KEY_ROOM_ID = :keyRoomId" +
          " AND AP.ACCOUNTING_POINT_ID =:accountingPointId";

  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Version private long version;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "accounting_point_key_room_id", nullable = false)
  private AccountingPointKeyRoom accountingPointKeyRoom;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "service_id", nullable = false)
  private Service service;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "direction_of_use_id", nullable = false)
  private DirectionOfUse directionOfUse;
}
