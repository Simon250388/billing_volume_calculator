package com.best.billing.volumecalculator.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCatalogDTO extends BaseEntityDTO {
    private String present;
}
