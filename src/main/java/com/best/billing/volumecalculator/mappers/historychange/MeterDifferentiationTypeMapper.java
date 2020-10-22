package com.best.billing.volumecalculator.mappers.historychange;

import com.best.billing.volumecalculator.dto.historychange.MeterDifferentiationTypeDTO;
import com.best.billing.volumecalculator.mappers.BaseHistoryMapper;
import com.best.billing.volumecalculator.models.historychange.MeterDifferentiationType;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
