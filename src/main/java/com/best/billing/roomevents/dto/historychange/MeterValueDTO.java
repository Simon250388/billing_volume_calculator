package com.best.billing.roomevents.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class MeterValueDTO extends BaseHistoryDTO {
    Long meterId;
    Integer value;

    public MeterValueDTO(Long id, Long version, String period, Long meterId, Integer value) {
        super(id, version, period);
        this.meterId = meterId;
        this.value = value;
    }


}
