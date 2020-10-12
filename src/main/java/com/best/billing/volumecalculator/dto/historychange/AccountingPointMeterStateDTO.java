package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointMeterStateDTO extends BaseHistoryDTO {
    private Long accountingPointKeyRoomServiceEntityId;
    private Long meterId;
    private Long meterStateId;

    public AccountingPointMeterStateDTO(Long id, String period, Long accountingPointKeyRoomServiceEntityId, Long meterId, Long meterStateId) {
        super(id, period);
        this.accountingPointKeyRoomServiceEntityId = accountingPointKeyRoomServiceEntityId;
        this.meterId = meterId;
        this.meterStateId = meterStateId;
    }
}
