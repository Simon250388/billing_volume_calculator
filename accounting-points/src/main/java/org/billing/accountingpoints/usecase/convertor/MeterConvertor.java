package org.billing.accountingpoints.usecase.convertor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.billing.accountingpoints.dto.MeterDto;
import org.billing.accountingpoints.usecase.dto.MeterPresentDto;

public class MeterConvertor implements PresentDtoConvertor<MeterPresentDto, MeterDto> {
  @Override
  public MeterPresentDto toPresent(MeterDto dto) {
    return MeterPresentDto.builder().build();
  }

  @Override
  public List<MeterPresentDto> toPresent(Set<MeterDto> dto) {
    return Collections.emptyList();
  }
}
