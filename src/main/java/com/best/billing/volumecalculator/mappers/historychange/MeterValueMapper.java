package com.best.billing.volumecalculator.mappers.historychange;

import com.best.billing.volumecalculator.dto.historychange.MeterValueDTO;
import com.best.billing.volumecalculator.mappers.BaseHistoryMapper;
import com.best.billing.volumecalculator.models.historychange.MeterValue;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseHistoryMapper.class)
public interface MeterValueMapper extends BaseHistoryMapper<MeterValue, MeterValueDTO> {
    @InheritConfiguration( name= "fromEntity" )
    @Mapping(source = "accountingPointKeyRoomServiceEntity.id", target = "accountingPointKeyRoomServiceEntityId")
    @Mapping(source = "meter.id", target = "meterId")
    @Override
    MeterValueDTO fromEntity(MeterValue source);

    @InheritConfiguration( name= "toEntity" )
    @Mapping(source = "accountingPointKeyRoomServiceEntityId", target = "accountingPointKeyRoomServiceEntity.id")
    @Mapping(source = "meterId", target = "meter.id")
    @Override
    MeterValue toEntity(MeterValueDTO source);
}
