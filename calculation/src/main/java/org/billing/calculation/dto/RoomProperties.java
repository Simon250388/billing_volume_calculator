package org.billing.calculation.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomProperties {

  double squareValue;
  int countOwner;
  int countResident;
  int countPrescribed;

  public Double getPeople() {
    return 0d;
  }
}
