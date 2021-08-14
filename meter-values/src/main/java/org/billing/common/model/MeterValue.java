package org.billing.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.billing.accountingpoints.model.AccountingPointKeyRoomServiceEntity;
import org.billing.accountingpoints.model.Meter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * История показаний прибора учета
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meter_values")
public class MeterValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_key_room_service_id", nullable = false)
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;
    @Column(nullable = false)
    private double value;
    /**
     * Тип события которым были зарегистрированы показания
     */
    @Column(name = "event_type_id", nullable = false)
    @Convert(converter = MethodProvideMeterValueConvertor.class)
    private MethodProvideMeterValue methodProvideMeterValue;
    /**
     * Ключ события которым были зарегестрированы показания
     */
    @Column(name = "event_id")
    private long eventId;
}
