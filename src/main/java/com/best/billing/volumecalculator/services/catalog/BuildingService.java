package com.best.billing.volumecalculator.services.catalog;

import com.best.billing.volumecalculator.dto.catalog.BuildingDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;
import org.jetbrains.annotations.NotNull;

public interface BuildingService extends BaseEntityService<BuildingDTO> {
    Iterable<BuildingDTO> findByDescriptionContaining(@NotNull final String subStr);
}
