package com.best.billing.servicebuilder.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AccountingPointServiceProviderDTO extends BaseHistoryDTO  {
    Long servicePartId;
    Long providerId;

    public AccountingPointServiceProviderDTO(Long id, String period, Long servicePartId, Long providerId) {
        super(id, period);
        this.servicePartId = servicePartId;
        this.providerId = providerId;
    }
}
