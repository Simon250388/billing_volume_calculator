package com.best.billing.volumecalculator.model;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.servicebuilder.models.historychange.MeterValue;
import com.best.billing.volumecalculator.convertors.CalculationMethodConvertor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Значение рассчитанного объем в периоде начисления
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "service_volume_values")
public class ServiceVolumeValue extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "stab_period_id", nullable = false)
    private StabPeriod stabPeriod;
    @Convert(converter = CalculationMethodConvertor.class)
    private CalculationMethod calculationMethod;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_value_start_id")
    private MeterValue meterValueStart;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "meter_value_end_id")
    private MeterValue meterValueEnd;
    @Column(nullable = false)
    private long volume;
    @Column(nullable = false)
    private long factVolume;
}
