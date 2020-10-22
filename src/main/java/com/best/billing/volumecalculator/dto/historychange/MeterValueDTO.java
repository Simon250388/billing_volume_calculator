package com.best.billing.volumecalculator.dto.historychange;

import com.best.billing.volumecalculator.dto.BaseHistoryDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class MeterValueDTO extends BaseHistoryDTO {
    Long MeterId;
    Integer Value;
}
