package com.best.billing.roomevents.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointServiceProviderDTO extends BaseHistoryDTO {
    Long servicePartId;
    Long providerId;

    public AccountingPointServiceProviderDTO(Long id, Long version, String period, Long servicePartId, Long providerId) {
        super(id, version, period);
        this.servicePartId = servicePartId;
        this.providerId = providerId;
    }
}
