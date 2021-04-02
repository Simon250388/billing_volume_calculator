package com.best.billing.base.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseCatalogDTO extends BaseEntityDTO {
    private final String present;

    public BaseCatalogDTO(Long id, Long version, String present) {
        super(id,version);
        this.present = present;
    }
}
