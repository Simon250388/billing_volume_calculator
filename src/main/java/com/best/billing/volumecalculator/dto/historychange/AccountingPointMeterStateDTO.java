package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountingPointMeterStateDTO extends BaseHistoryDTO {
    private long accountingPointKeyRoomServiceEntityId;
    private long meterId;
    private long meterStateId;
}
