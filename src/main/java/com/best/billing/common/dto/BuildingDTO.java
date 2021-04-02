package com.best.billing.common.dto;

import com.best.billing.base.dto.BaseCatalogDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class BuildingDTO extends BaseCatalogDTO {
    public BuildingDTO(Long id, Long version, String present) {
        super(id, version, present);
    }
}
