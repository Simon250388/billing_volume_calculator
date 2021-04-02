package com.best.billing.roomevents.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class MeterDifferentiationTypeDTO extends BaseHistoryDTO {
    public MeterDifferentiationTypeDTO(Long id, Long version, String period) {
        super(id, version, period);
    }
}
