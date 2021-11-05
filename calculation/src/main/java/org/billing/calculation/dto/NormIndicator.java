package org.billing.calculation.dto;

import java.util.function.Function;

public enum NormIndicator {
  SQUARE(RoomProperties::getSquareValue),
  PEOPLE(RoomProperties::getPeople);

  private final Function<RoomProperties, Double> normIndicatorHandler;

  NormIndicator(Function<RoomProperties, Double> normIndicatorHandler) {
    this.normIndicatorHandler = normIndicatorHandler;
  }

  public Function<RoomProperties, Double> getHandler() {
    return normIndicatorHandler;
  }
}
