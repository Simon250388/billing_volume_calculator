package com.best.billingvolumecalculator.mappers.entity;

import com.best.billingvolumecalculator.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billingvolumecalculator.mappers.BaseEntityMapper;
import com.best.billingvolumecalculator.models.entity.AccountingPointKeyRoom;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface AccountingPointKeyRoomMapper extends BaseEntityMapper<AccountingPointKeyRoom, AccountingPointKeyRoomDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Override
    AccountingPointKeyRoomDTO fromEntity(AccountingPointKeyRoom source);

    @InheritConfiguration( name= "toEntity" )
    @Override
    AccountingPointKeyRoom toEntity(AccountingPointKeyRoomDTO source);
}
