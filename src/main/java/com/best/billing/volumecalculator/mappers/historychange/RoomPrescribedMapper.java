package com.best.billing.volumecalculator.mappers.historychange;

import com.best.billing.volumecalculator.dto.historychange.RoomPrescribedDTO;
import com.best.billing.volumecalculator.mappers.BaseHistoryMapper;
import com.best.billing.volumecalculator.models.historychange.RoomPrescribed;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomPrescribedMapper extends BaseHistoryMapper<RoomPrescribed, RoomPrescribedDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "keyRoom.id", target = "keyRoomId")
    @Mapping(source = "prescribedCount", target = "prescribedCount")
    @Override
    RoomPrescribedDTO fromEntity(RoomPrescribed source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "keyRoomId", target = "keyRoom.id")
    @Mapping(source = "prescribedCount", target = "prescribedCount")
    @Override
    RoomPrescribed toEntity(RoomPrescribedDTO source);
}
