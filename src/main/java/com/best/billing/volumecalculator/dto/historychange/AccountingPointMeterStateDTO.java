package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountingPointMeterStateDTO extends BaseHistoryDTO {
    private Long accountingPointKeyRoomServiceEntityId;
    private Long meterId;
    private Long meterStateId;
}
