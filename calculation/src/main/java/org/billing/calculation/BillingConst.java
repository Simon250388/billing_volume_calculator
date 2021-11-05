package org.billing.calculation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BillingConst {

  private final int VOLUME_SCALE = 3;

  public int getVolumeScale() {
    return VOLUME_SCALE;
  }
}
