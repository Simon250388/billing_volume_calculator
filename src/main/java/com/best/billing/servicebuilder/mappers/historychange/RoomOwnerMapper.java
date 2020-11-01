package com.best.billing.servicebuilder.mappers.historychange;

import com.best.billing.servicebuilder.dto.historychange.RoomOwnerDTO;
import com.best.billing.base.mappers.BaseHistoryMapper;
import com.best.billing.servicebuilder.models.historychange.RoomOwner;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface RoomOwnerMapper extends BaseHistoryMapper<RoomOwner, RoomOwnerDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "keyRoom.id", target = "keyRoomId")
    @Mapping(source = "ownerCount", target = "ownerCount")
    @Override
    RoomOwnerDTO fromEntity(RoomOwner source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "keyRoomId", target = "keyRoom.id")
    @Mapping(source = "ownerCount", target = "ownerCount")
    @Override
    RoomOwner toEntity(RoomOwnerDTO source);
}
