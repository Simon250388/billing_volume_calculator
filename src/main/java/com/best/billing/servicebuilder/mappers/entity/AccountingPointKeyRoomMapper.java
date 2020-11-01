package com.best.billing.servicebuilder.mappers.entity;

import com.best.billing.servicebuilder.dto.entity.AccountingPointKeyRoomDTO;
import com.best.billing.base.mappers.BaseEntityMapper;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseEntityMapper.class)
public interface AccountingPointKeyRoomMapper extends BaseEntityMapper<AccountingPointKeyRoom, AccountingPointKeyRoomDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "keyRoom.id", target = "keyRoomId")
    @Mapping(source = "accountingPoint.id", target = "accountingPointId")
    @Override
    AccountingPointKeyRoomDTO fromEntity(AccountingPointKeyRoom source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "keyRoomId", target = "keyRoom.id")
    @Mapping(source = "accountingPointId", target = "accountingPoint.id")
    @Override
    AccountingPointKeyRoom toEntity(AccountingPointKeyRoomDTO source);
}
