package org.billing.calculation.model;

import lombok.*;
import org.billing.common.model.DirectionOfUse;
import org.billing.common.model.Service;
import org.billing.rooms.model.SquareType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Настройка расчета объема услуги по направлению использования
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "calculation_method_direction_of_use")
public class CalculationMethodByDirectionOfUse  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @Column(name = "period_fact" )
    private LocalDateTime periodFact;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "direction_of_use_id", nullable = false)
    private DirectionOfUse directionOfUse;
    @Column(name = "square_type_id", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SquareType squareType;
    /**
     * Начислять норматив по дням
     */
    @Column(nullable = false)
    private Boolean normByDay;
    /**
     * Начислять за полный месяц в периодах изменения сезонности
     */
    @Column(nullable = false)
    private Boolean calculateFullMonth;
}
