package com.best.billing.common.mappers.catalog;

import com.best.billing.common.dto.BuildingDTO;
import com.best.billing.base.mappers.BaseCatalogMapper;
import com.best.billing.base.mappers.BaseEntityMapper;
import com.best.billing.common.model.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseEntityMapper.class)
public interface BuildingMapper extends BaseCatalogMapper<Building, BuildingDTO> {
}
