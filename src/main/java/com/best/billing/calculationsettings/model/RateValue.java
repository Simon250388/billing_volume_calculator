package com.best.billing.calculationsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.model.RateGroup;
import com.best.billing.common.model.Service;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Значения тарифов услуг
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "rate_values")
public class RateValue implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rate_group_id", nullable = false)
    private RateGroup rateGroup;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    /**
     * Значение тарифа
     */
    @Column(nullable = false)
    private Integer value;
}
