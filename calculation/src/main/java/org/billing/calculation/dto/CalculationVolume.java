package org.billing.calculation.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CalculationVolume {
    UUID skaleId;
    UUID rateZoneId;
    double rateValue;
    BigDecimal volume;
    BigDecimal volumeFact;
    BigDecimal sum;
    BigDecimal sumFact;
}
