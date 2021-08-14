package org.billing.calculation.model;

import lombok.*;
import org.billing.common.model.Seasonality;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Период сезонности услуги
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "period_seasonality")
public class PeriodSeasonality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @Column(nullable = false)
    private Integer year;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "seasonality_id", nullable = false)
    private Seasonality seasonality;
    /**
     * Месяц начало действия сезонности
     */
    @Column(nullable = false)
    private Integer monthStart;
    /**
     * День месяца начала действия сезонности
     */
    @Column(nullable = false)
    private Integer dayStart;
    /**
     * Месяц окончания действия сезонности
     */
    @Column(nullable = false)
    private Integer monthEnd;
    /**
     * День месяца окончания действия сезонности
     */
    @Column(nullable = false)
    private Integer dayEnd;
}
