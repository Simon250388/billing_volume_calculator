package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.convertors.SquareTypeConvertor;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import com.best.billing.common.model.enums.SquareType;
import lombok.*;

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
public class CalculationMethodByDirectionOfUse implements BaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(name = "period", nullable = false)
    private LocalDateTime period;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "direction_of_use_id", nullable = false)
    private DirectionOfUse directionOfUse;
    @JoinColumn(name = "square_type_id", nullable = false)
    @Convert(converter =SquareTypeConvertor.class)
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
