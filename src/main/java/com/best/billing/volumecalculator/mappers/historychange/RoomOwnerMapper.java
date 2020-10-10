package com.best.billing.volumecalculator.mappers.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomOwnerDTO;
import com.best.billing.volumecalculator.mappers.BaseHistoryMapper;
import com.best.billing.volumecalculator.models.historychange.RoomOwner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomOwnerMapper extends BaseHistoryMapper<RoomOwner, RoomOwnerDTO> {
    //@InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "keyRoom.id", target = "keyRoomId")
    @Mapping(source = "ownerCount", target = "ownerCount")
    @Override
    RoomOwnerDTO fromEntity(RoomOwner source);

    //@InheritConfiguration( name= "toEntity" )
    @Mapping(source = "keyRoomId", target = "keyRoom.id")
    @Mapping(source = "ownerCount", target = "ownerCount")
    @Override
    RoomOwner toEntity(RoomOwnerDTO source);
}
