package org.billing.calculation.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AvgRateZoneVolume {
    UUID rateZoneId;
    BigDecimal avgVolume;
    BigDecimal avgVolumeFact;
}
