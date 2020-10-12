package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointServiceStateDTO extends BaseHistoryDTO {
    private Long accountingPointKeyRoomServiceEntityId;
    private Boolean active;
    private Long meterId;
    private Long serviceId;
    private String meterStatePeriod;

    public AccountingPointServiceStateDTO(Long id, String period, Long accountingPointKeyRoomServiceEntityId, boolean active, long meterId, Long serviceId, String meterStatePeriod) {
        super(id, period);
        this.accountingPointKeyRoomServiceEntityId = accountingPointKeyRoomServiceEntityId;
        this.active = active;
        this.meterId = meterId;
        this.serviceId = serviceId;
        this.meterStatePeriod = meterStatePeriod;
    }
}
