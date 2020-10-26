package com.best.billing.servicebuilder.mappers.catalog;

import com.best.billing.servicebuilder.dto.catalog.BuildingDTO;
import com.best.billing.servicebuilder.mappers.BaseCatalogMapper;
import com.best.billing.servicebuilder.mappers.BaseEntityMapper;
import com.best.billing.common.model.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseEntityMapper.class)
public interface BuildingMapper extends BaseCatalogMapper<Building, BuildingDTO> {
}
