package com.best.billing.commonsettings.model;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import com.best.billing.servicebuilder.models.enums.SquareType;
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
public class CalculationMethodByDirectionOfUse extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "direction_of_use_id", nullable = false)
    private DirectionOfUse directionOfUse;

    @ManyToOne
    @JoinColumn(name = "square_type_id", nullable = false)
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
