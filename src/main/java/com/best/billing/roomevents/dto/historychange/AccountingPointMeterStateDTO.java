package com.best.billing.roomevents.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointMeterStateDTO extends BaseHistoryDTO {
    Long accountingPointKeyRoomServiceEntityId;
    Long meterId;
    Long meterStateId;

    public AccountingPointMeterStateDTO(Long id, Long version, String period, Long accountingPointKeyRoomServiceEntityId, Long meterId, Long meterStateId) {
        super(id, version, period);
        this.accountingPointKeyRoomServiceEntityId = accountingPointKeyRoomServiceEntityId;
        this.meterId = meterId;
        this.meterStateId = meterStateId;
    }
}
