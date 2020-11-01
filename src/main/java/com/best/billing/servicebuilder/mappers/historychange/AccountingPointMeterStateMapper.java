package com.best.billing.servicebuilder.mappers.historychange;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointMeterStateDTO;
import com.best.billing.base.mappers.BaseHistoryMapper;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface AccountingPointMeterStateMapper extends BaseHistoryMapper<AccountingPointMeterState, AccountingPointMeterStateDTO>  {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "accountingPointKeyRoomServiceEntity.id", target = "accountingPointKeyRoomServiceEntityId")
    @Mapping(source = "meter.id", target = "meterId")
    @Mapping(source = "meterState.id", target = "meterStateId")
    @Override
    AccountingPointMeterStateDTO fromEntity(AccountingPointMeterState source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "accountingPointKeyRoomServiceEntityId", target = "accountingPointKeyRoomServiceEntity.id")
    @Mapping(source = "meterId", target = "meter.id")
    @Mapping(source = "meterStateId", target = "meterState.id")
    @Override
    AccountingPointMeterState toEntity(AccountingPointMeterStateDTO source);
}
