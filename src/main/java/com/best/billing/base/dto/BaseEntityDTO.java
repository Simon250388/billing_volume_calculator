package com.best.billing.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseEntityDTO {
    private final Long id;
    private final Long version;
}
