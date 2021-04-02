package com.best.billing.roomevents.mappers.historychange;

import com.best.billing.roomevents.dto.historychange.MeterValueDTO;
import com.best.billing.base.mappers.BaseHistoryMapper;
import com.best.billing.roomevents.models.MeterValue;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface MeterValueMapper extends BaseHistoryMapper<MeterValue, MeterValueDTO> {
    @InheritConfiguration( name= "fromEntity" )
    //@Mapping(source = "accountingPointKeyRoomServiceEntity.id", target = "accountingPointKeyRoomServiceEntityId")
    //@Mapping(source = "meter.id", target = "meterId")
    @Override
    MeterValueDTO fromEntity(MeterValue source);

    @InheritConfiguration( name= "toEntity" )
    //@Mapping(source = "accountingPointKeyRoomServiceEntityId", target = "accountingPointKeyRoomServiceEntity.id")
    //@Mapping(source = "meterId", target = "meter.id")
    @Override
    MeterValue toEntity(MeterValueDTO source);
}