package com.best.billing.common.services.catalog;

import com.best.billing.common.dto.BuildingDTO;
import com.best.billing.base.service.BaseEntityService;
import lombok.NonNull;

public interface BuildingService extends BaseEntityService<BuildingDTO> {
    Iterable<BuildingDTO> findByDescriptionContaining(@NonNull final String subStr);
}
