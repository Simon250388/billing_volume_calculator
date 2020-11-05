package com.best.billing.volumecalculator.model;

import com.best.billing.base.model.BaseEntity;
import com.best.billing.servicebuilder.models.historychange.MeterValue;
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
    @ManyToOne
    @JoinColumn(name = "stab_period_id", nullable = false)
    private StabPeriod stabPeriod;
    @ManyToOne
    @JoinColumn(name = "calculation_method_id", nullable = false)
    private CalculationMethod calculationMethod;
    @ManyToOne
    @JoinColumn(name = "meter_value_start_id")
    private MeterValue meterValueStart;
    @ManyToOne
    @JoinColumn(name = "meter_value_end_id")
    private MeterValue meterValueEnd;
    @Column(nullable = false)
    private Integer volume;
    @Column(nullable = false)
    private Integer factVolume;

}
