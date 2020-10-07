package com.best.billing.volumecalculator.mappers.entity;

import com.best.billing.volumecalculator.dto.entity.KeyRoomDTO;
import com.best.billing.volumecalculator.mappers.BaseEntityMapper;
import com.best.billing.volumecalculator.models.entity.KeyRoom;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KeyRoomMapper extends BaseEntityMapper<KeyRoom, KeyRoomDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "building.id", target = "buildingId")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "privateSector", target = "privateSector")
    @Override
    KeyRoomDTO fromEntity(KeyRoom source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "buildingId", target = "building.id")
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "privateSector", target = "privateSector")
    @Override
    KeyRoom toEntity(KeyRoomDTO source);
}
