package com.best.billing.base.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseHistoryDTO extends BaseEntityDTO {
    private final String period;

    public BaseHistoryDTO(Long id, Long version, String period) {
        super(id, version);
        this.period = period;
    }

}
