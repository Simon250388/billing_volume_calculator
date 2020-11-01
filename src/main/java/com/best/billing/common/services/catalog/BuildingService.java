package com.best.billing.common.services.catalog;

import com.best.billing.common.dto.BuildingDTO;
import com.best.billing.base.service.BaseEntityService;
import org.jetbrains.annotations.NotNull;

public interface BuildingService extends BaseEntityService<BuildingDTO> {
    Iterable<BuildingDTO> findByDescriptionContaining(@NotNull final String subStr);
}
