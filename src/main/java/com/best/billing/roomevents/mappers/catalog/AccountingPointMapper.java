package com.best.billing.roomevents.mappers.catalog;

import com.best.billing.roomevents.dto.catalog.AccountingPointDTO;
import com.best.billing.base.mappers.BaseCatalogMapper;
import com.best.billing.base.mappers.BaseEntityMapper;
import com.best.billing.common.model.AccountingPoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseEntityMapper.class)
public interface AccountingPointMapper extends BaseCatalogMapper<AccountingPoint, AccountingPointDTO> {
}
