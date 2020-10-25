package com.best.billing.servicebuilder.mappers.historychange;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointServiceProviderDTO;
import com.best.billing.servicebuilder.mappers.BaseEntityMapper;
import com.best.billing.servicebuilder.mappers.BaseHistoryMapper;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceProvider;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface AccountingPointServiceProviderMapper extends BaseEntityMapper<AccountingPointServiceProvider, AccountingPointServiceProviderDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "provider.id", target = "providerId")
    @Override
    AccountingPointServiceProviderDTO fromEntity(AccountingPointServiceProvider source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "providerId", target = "provider.id")
    @Override
    AccountingPointServiceProvider toEntity(AccountingPointServiceProviderDTO source);
}
