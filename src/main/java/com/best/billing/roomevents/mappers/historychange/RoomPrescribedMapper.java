package com.best.billing.roomevents.mappers.historychange;

import com.best.billing.roomevents.dto.historychange.RoomPrescribedDTO;
import com.best.billing.base.mappers.BaseHistoryMapper;
import com.best.billing.roomevents.models.RoomPrescribed;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface RoomPrescribedMapper extends BaseHistoryMapper<RoomPrescribed, RoomPrescribedDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "keyRoom.id", target = "keyRoomId")
    @Mapping(source = "prescribedCount", target = "prescribedCount")
    @Override
    RoomPrescribedDTO
    fromEntity(RoomPrescribed source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "keyRoomId", target = "keyRoom.id")
    @Mapping(source = "prescribedCount", target = "prescribedCount")
    @Override
    RoomPrescribed toEntity(RoomPrescribedDTO source);
}
