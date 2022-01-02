package org.billing.calculation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceOfAccountingPointStabilityPeriod {
  /** Начало периода начисления */
  LocalDate calculationPeriodStart;

  /** Окончание периода начисления */
  LocalDate calculationPeriodEnd;
  /** Начало стабильного периода */
  LocalDateTime periodStart;
  /** окончание стабильного периода */
  LocalDateTime periodEnd;
  /** Свойства помещения */
  RoomProperties roomProperties;
  /** Свойства точки учета */
  AccountingPointProperties accountingPoints;
  /** Услуга на точке учета */
  UUID serviceId;
  /** Признак виртуального события */
  boolean virtual;
  /** Значение норматива потребления */
  double normValue;

  double coefficientNormValue;

  NormIndicator normIndicator;

  @Builder.Default AvgScaleVolume[] avgScaleVolumes = new AvgScaleVolume[0];
  @Builder.Default ScaleMeterValue[] scaleMeterValuesStartOrPeriod = new ScaleMeterValue[0];
  @Builder.Default ScaleMeterValue[] scaleMeterValuesEndOrPeriod = new ScaleMeterValue[0];

  boolean meterValuesProvideForAllPointsOfServices;

  boolean avgVolumeForAllPointsOfServices;

  public boolean isServiceActive() {
    return accountingPoints.isServiceActive();
  }

  public boolean isMeterActive() {
    return accountingPoints.isMeterActive();
  }

  /** Отношение длительности стабльного периода к длительности периода начисления */
  public double periodPercent() {
    return (durationsOfStabilityPeriod() / daysOfCalculationPeriod());
  }

  /**
   * Показатель для расчета нормы
   *
   * @return количество человек или значение площади
   */
  public double normIndicator() {
    return normIndicator.getHandler().apply(roomProperties);
  }

  private long daysOfCalculationPeriod() {
    return ChronoUnit.DAYS.between(calculationPeriodStart, calculationPeriodEnd);
  }

  private long durationsOfStabilityPeriod() {
    return ChronoUnit.DAYS.between(periodStart, periodEnd);
  }
}
