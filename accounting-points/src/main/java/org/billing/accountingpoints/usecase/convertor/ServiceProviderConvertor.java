package org.billing.accountingpoints.usecase.convertor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDtoValue;
import org.billing.accountingpoints.usecase.dto.ServiceProviderPresentDto;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderConvertor
    implements PresentDtoConvertor<
        ServiceProviderPresentDto, AccountingPointServiceProviderDtoValue> {
  @Override
  public ServiceProviderPresentDto toPresent(AccountingPointServiceProviderDtoValue dto) {
    return null;
  }

  @Override
  public List<ServiceProviderPresentDto> toPresent(
      Set<AccountingPointServiceProviderDtoValue> dtos) {
    return dtos.stream()
        .map(
            item ->
                ServiceProviderPresentDto.builder()
                    .providerId(item.getProviderId())
                    .serviceId(item.getServiceId())
                    .build())
        .collect(Collectors.toList());
  }
}
