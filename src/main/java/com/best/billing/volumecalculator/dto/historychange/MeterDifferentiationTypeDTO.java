package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class MeterDifferentiationTypeDTO extends BaseHistoryDTO {
    public MeterDifferentiationTypeDTO(Long id, String period) {
        super(id, period);
    }
}
