package com.best.billing.volumecalculator.model;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.servicebuilder.models.historychange.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stability_periods")
public class StabPeriod extends BaseEntity {

    public static final String FIND_ALL_LAST_ON_CURRENT_CALCULATION_PERIOD = "StabPeriod.findAllByCurrentCalculationPeriod";

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_id")
    private AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_service_state_id", nullable = false)
    private AccountingPointServiceState accountingPointServiceState;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_service_provider_id")
    private AccountingPointServiceProvider accountingPointServiceProvider;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_rate_group_id")
    private RoomRateGroup roomRateGroup;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accounting_point_meter_state_id")
    private AccountingPointMeterState accountingPointMeterState;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_square_id")
    private RoomSquare roomSquare;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_differentiation_type_id")
    private MeterDifferentiationType meterDifferentiationType;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_owner_id")
    private RoomOwner roomOwner;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_prescribed_id")
    private RoomPrescribed roomPrescribed;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_resident_id")
    private RoomResident roomResident;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_common_square_id")
    private RoomSquare roomCommonSquare;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_service_key_norm_id")
    private RoomServiceKeyNorm roomServiceKeyNorm;

    /**
     * Период начисления в котором появилась запись
     */
    @Column(nullable = false)
    private LocalDate calculationPeriod;

    /**
     * Дата регистрации записи которая которая произошла в реальной жизни но мы регистриурем ее задним числом
     */
    @Column(nullable = false)
    private LocalDate registrationPeriodFact;

    /**
     * Дата регистрации записи
     */
    @Column(nullable = false)
    private LocalDate registrationPeriod;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "next_row_id")
    private StabPeriod nextRow;
}
