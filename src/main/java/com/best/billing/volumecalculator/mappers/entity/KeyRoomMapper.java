package com.best.billingvolumecalculator.mappers.entity;

import com.best.billingvolumecalculator.dto.entity.KeyRoomDTO;
import com.best.billingvolumecalculator.mappers.BaseEntityMapper;
import com.best.billingvolumecalculator.models.entity.KeyRoom;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface KeyRoomMapper extends BaseEntityMapper<KeyRoom, KeyRoomDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Override
    KeyRoomDTO fromEntity(KeyRoom source);

    @InheritConfiguration( name= "toEntity" )
    @Override
    KeyRoom toEntity(KeyRoomDTO source);
}
