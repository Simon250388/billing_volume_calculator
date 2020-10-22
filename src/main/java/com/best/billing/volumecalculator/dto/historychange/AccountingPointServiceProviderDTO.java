package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
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
