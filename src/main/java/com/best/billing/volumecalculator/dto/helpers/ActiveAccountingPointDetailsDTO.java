package com.best.billing.volumecalculator.dto.helpers;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class ActiveAccountingPointDetailsDTO {
    Long keyRoomId;
    Long accountingPointId;
    Long serviceId;
    Long providerId;
    Long directionOfUseId;
    Boolean isActive;
    Long meterId;
    Boolean meterIsActive;
    Date meterStateChangeAt;
    Long differentiationTypeId;
    Integer lastMeterValue;
}
