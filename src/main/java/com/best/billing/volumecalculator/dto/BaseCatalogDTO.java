package com.best.billing.volumecalculator.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseCatalogDTO extends BaseEntityDTO {
    private String present;
}
