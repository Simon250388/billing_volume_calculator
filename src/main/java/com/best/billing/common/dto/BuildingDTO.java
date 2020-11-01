package com.best.billing.common.dto;

import com.best.billing.base.dto.BaseCatalogDTO;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class BuildingDTO extends BaseCatalogDTO {
    public BuildingDTO(Long id, String present) {
        super(id, present);
    }
}
