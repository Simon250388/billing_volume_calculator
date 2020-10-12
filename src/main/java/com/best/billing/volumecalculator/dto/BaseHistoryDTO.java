package com.best.billing.volumecalculator.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseHistoryDTO extends BaseEntityDTO {
    private String period;

    public BaseHistoryDTO(Long id, String period) {
        super(id);
        this.period = period;
    }
}
