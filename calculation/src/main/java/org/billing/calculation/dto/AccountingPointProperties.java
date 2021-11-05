package org.billing.calculation.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountingPointProperties {
    UUID serice;
    boolean serviceActive;
    boolean meterActive;
}
