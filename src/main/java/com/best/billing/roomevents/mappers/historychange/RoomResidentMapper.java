package com.best.billing.roomevents.mappers.historychange;

import com.best.billing.roomevents.dto.historychange.RoomResidentdDTO;
import com.best.billing.base.mappers.BaseHistoryMapper;
import com.best.billing.roomevents.models.RoomResident;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface RoomResidentMapper extends BaseHistoryMapper<RoomResident, RoomResidentdDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "keyRoom.id", target = "keyRoomId")
    @Mapping(source = "residentCount", target = "residentCount")
    @Override
    RoomResidentdDTO fromEntity(RoomResident source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "keyRoomId", target = "keyRoom.id")
    @Mapping(source = "residentCount", target = "residentCount")
    @Override
    RoomResident toEntity(RoomResidentdDTO source);
}
