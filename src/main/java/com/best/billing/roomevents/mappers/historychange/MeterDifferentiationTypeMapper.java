package com.best.billing.roomevents.mappers.historychange;

import com.best.billing.roomevents.dto.historychange.MeterDifferentiationTypeDTO;
import com.best.billing.base.mappers.BaseHistoryMapper;
import com.best.billing.roomevents.models.MeterDifferentiationType;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface MeterDifferentiationTypeMapper  extends BaseHistoryMapper<MeterDifferentiationType, MeterDifferentiationTypeDTO>{
    @InheritConfiguration( name= "fromEntity" )
//    @Mapping(source = "accountingPointKeyRoomServiceEntity.id", target = "accountingPointKeyRoomServiceEntityId")
//    @Mapping(source = "meter.id", target = "meterId")
    @Override
    MeterDifferentiationTypeDTO fromEntity(MeterDifferentiationType source);

    @InheritConfiguration( name= "toEntity" )
//    @Mapping(source = "accountingPointKeyRoomServiceEntityId", target = "accountingPointKeyRoomServiceEntity.id")
//    @Mapping(source = "meterId", target = "meter.id")
    @Override
    MeterDifferentiationType toEntity(MeterDifferentiationTypeDTO source);
}