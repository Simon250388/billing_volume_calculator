package com.best.billing.volumecalculator.mappers.catalog;

import com.best.billing.volumecalculator.dto.catalog.BuildingDTO;
import com.best.billing.volumecalculator.mappers.BaseCatalogMapper;
import com.best.billing.volumecalculator.models.catalog.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildingMapper extends BaseCatalogMapper<Building, BuildingDTO> {
}
