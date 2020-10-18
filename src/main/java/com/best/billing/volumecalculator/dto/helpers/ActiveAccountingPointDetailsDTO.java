package com.best.billing.volumecalculator.dto.helpers;

import lombok.Value;

import java.util.Date;

@Value

public class ActiveAccountingPointDetailsDTO {
    Long keyRoomId;
    Long accountingPointId;
    Long serviceId;
    Long providerId;
    Boolean isActive;
    Long meterId;
    Boolean meterIsActive;
    Date meterStateChangeAt;
    Long differentiationTypeId;
    Integer lastMeterValue;
}
