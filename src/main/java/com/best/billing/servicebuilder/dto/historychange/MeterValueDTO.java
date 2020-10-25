package com.best.billing.servicebuilder.dto.historychange;

import com.best.billing.base.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class MeterValueDTO extends BaseHistoryDTO {
    Long meterId;
    Integer value;

    public MeterValueDTO(Long id, String period, Long meterId, Integer value) {
        super(id, period);
        this.meterId = meterId;
        this.value = value;
    }




}
