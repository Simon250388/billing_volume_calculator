package com.best.billing.volumecalculator.model;

import com.best.billing.base.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "calculation_methods" )
@Immutable
public class CalculationMethod extends BaseEntity {
    public static final long METHOD_BY_METER_ID = 1;
    public static final CalculationMethod METHOD_BY_METER = CalculationMethod.builder().id(CalculationMethod.METHOD_BY_METER_ID).build();
}
