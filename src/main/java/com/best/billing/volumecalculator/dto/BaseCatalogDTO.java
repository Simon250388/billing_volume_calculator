package com.best.billing.volumecalculator.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseCatalogDTO extends BaseEntityDTO {
    private String present;

    public BaseCatalogDTO(Long id, String present) {
        super(id);
        this.present = present;
    }
}
