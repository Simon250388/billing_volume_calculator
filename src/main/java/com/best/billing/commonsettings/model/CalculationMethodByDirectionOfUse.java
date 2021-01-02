package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseHistory;
import com.best.billing.common.convertors.SquareTypeConvertor;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import com.best.billing.common.model.enums.SquareType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Настройка расчета объема услуги по направлению использования
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "calculation_method_direction_of_use")
public class CalculationMethodByDirectionOfUse extends BaseHistory {
    public static final String QUERY_FIND_ALL_LAST_BY_PERIOD = "CalculationMethodByDirectionOfUse.findAllLastByPeriod";
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
