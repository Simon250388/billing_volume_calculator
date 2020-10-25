package com.best.billing.volumecalculator.model;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.servicebuilder.models.catalog.*;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stability_periods")
@Immutable
@NamedQuery(
        name = StabPeriod.FIND_ALL_LAST_ON_CURRENT_CALCULATION_PERIOD,
        query = " FROM StabPeriod stb" +
                " WHERE (stb.keyRoom, stb.accountingPoint, stb.service, stb.calculationPeriod, stb.registrationPeriod) IN" +
                "   (SELECT stb.keyRoom, stb.accountingPoint, stb.service, stb.calculationPeriod, MAX(stb.registrationPeriod" +
                "   FROM StabPeriod stb" +
                "   WHERE (stb.keyRoom, stb.accountingPoint, stb.service, stb.calculationPeriod) IN " +
                "       (SELECT stb.keyRoom, stb.accountingPoint, stb.service, MAX(stb.calculationPeriod)" +
                "       FROM StabPeriod stb" +
                "       WHERE stb.calculationPeriod < :currentCalculationPeriod" +
                "       GROUP BY stb.keyRoom, stb.accountingPoint, stb.service)" +
                "   GROUP BY stb.keyRoom, stb.accountingPoint, stb.service, stb.calculationPeriod)")
public class StabPeriod extends BaseEntity {

    public static final String FIND_ALL_LAST_ON_CURRENT_CALCULATION_PERIOD = "StabPeriod.findAllByCurrentCalculationPeriod";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "key_room_id", nullable = false)
    private KeyRoom keyRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accounting_point_id")
    private AccountingPoint accountingPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accounting_point_service_state_id", nullable = false)
    private AccountingPointServiceState accountingPointServiceState;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "direction_of_use_id",nullable = false)
    private DirectionOfUse directionOfUse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accounting_point_service_provider_id")
    private AccountingPointServiceProvider accountingPointServiceProvider;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_rate_group_id")
    private RoomRateGroup roomRateGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accounting_point_meter_state_id")
    private AccountingPointMeterState accountingPointMeterState;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meter_differentiation_type_id")
    private MeterDifferentiationType meterDifferentiationType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_owner_id")
    private RoomOwner roomOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_prescribed_id")
    private RoomPrescribed roomPrescribed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_resident_id")
    private RoomResident roomResident;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_common_square_id")
    private RoomSquare roomCommonSquare;

    /**
     * Период начисления в котором появилась запись
     */
    @Column(nullable = false)
    private Date calculationPeriod;

    /**
     * Дата регистрации записи которая которая произошла в реальной жизни но мы регистриурем ее задним числом
     */
    @Column(nullable = false)
    private Date registrationPeriodFact;

    /**
     * Дата регистрации записи
     */
    @Column(nullable = false)
    private Date registrationPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_id")
    private StabPeriod previous;
}
