package com.best.billing.volumecalculator.services.catalog;

import com.best.billing.volumecalculator.dto.catalog.BuildingDTO;
import com.best.billing.volumecalculator.services.BaseEntityService;

import java.util.List;

public interface BuildingService extends BaseEntityService<BuildingDTO> {
    List<BuildingDTO> findByDescriptionContaining(String subStr);
}