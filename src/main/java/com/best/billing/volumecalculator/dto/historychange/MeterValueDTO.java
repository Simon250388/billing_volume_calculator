package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
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
