package com.best.billingvolumecalculator.mappers.catalog;

import com.best.billingvolumecalculator.dto.catalog.AccountingPointDTO;
import com.best.billingvolumecalculator.mappers.BaseCatalogMapper;
import com.best.billingvolumecalculator.models.catalog.AccountingPoint;
import org.mapstruct.InheritConfiguration;

public interface AccountingPointMapper extends BaseCatalogMapper<AccountingPoint, AccountingPointDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Override
    AccountingPointDTO fromEntity(AccountingPoint source);
    @InheritConfiguration( name= "toEntity" )
    @Override
    AccountingPoint toEntity(AccountingPointDTO source);
}
