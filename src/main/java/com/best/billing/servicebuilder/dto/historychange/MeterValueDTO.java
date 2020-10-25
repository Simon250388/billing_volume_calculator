package com.best.billing.servicebuilder.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class MeterValueDTO extends BaseHistoryDTO {
    Long MeterId;
    Integer Value;

    public MeterValueDTO(Long id, String period, Long meterId, Integer value) {
        super(id, period);
        MeterId = meterId;
        Value = value;
    }
}
