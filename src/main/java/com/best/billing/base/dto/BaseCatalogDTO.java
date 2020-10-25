package com.best.billing.base.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseCatalogDTO extends BaseEntityDTO {
    private final String present;

    public BaseCatalogDTO(Long id, String present) {
        super(id);
        this.present = present;
    }
}
