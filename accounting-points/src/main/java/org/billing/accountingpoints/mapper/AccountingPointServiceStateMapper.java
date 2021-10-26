package org.billing.accountingpoints.mapper;

import java.util.Collection;
import java.util.stream.Collectors;
import org.billing.accountingpoints.dto.AccountingPointServiceStateDto;
import org.billing.accountingpoints.model.AccountingPointServiceState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccountingPointServiceStateMapper
    implements ModelMapper<AccountingPointServiceState, AccountingPointServiceStateDto> {

  @Override
  @Mapping(target = "id", source = "dto.id")
  @Mapping(target = "period", source = "dto.period")
  @Mapping(target = "periodFact", source = "dto.periodFact")
  //    @Mapping(target = "serviceId", source = "dto.serviceId")
  public abstract AccountingPointServiceState toModel(final AccountingPointServiceStateDto dto);

  @Override
  public Collection<AccountingPointServiceState> toModel(
      final Collection<AccountingPointServiceStateDto> dtos) {
    return dtos.stream().map(this::toModel).collect(Collectors.toList());
  }

  @Override
  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "period", source = "model.period")
  @Mapping(target = "periodFact", source = "model.periodFact")
  //@Mapping(target = "serviceId", source = "model.serviceId")
  public abstract AccountingPointServiceStateDto toDto(final AccountingPointServiceState model);

  @Override
  public Collection<AccountingPointServiceStateDto> toDto(
      final Collection<AccountingPointServiceState> models) {
    return models.stream().map(this::toDto).collect(Collectors.toList());
  }
}
