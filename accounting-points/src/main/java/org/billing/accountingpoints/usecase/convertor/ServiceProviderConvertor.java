package org.billing.accountingpoints.usecase.convertor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.billing.accountingpoints.dto.AccountingPointServiceProviderDto;
import org.billing.accountingpoints.request.ServiceProviderRequest;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderConvertor
    implements PresentDtoConvertor<
        ServiceProviderRequest, AccountingPointServiceProviderDto> {
  @Override
  public ServiceProviderRequest toPresent(AccountingPointServiceProviderDto dto) {
    return null;
  }

  @Override
  public List<ServiceProviderRequest> toPresent(
      Set<AccountingPointServiceProviderDto> dtos) {
    return dtos.stream()
        .map(
            item ->
                ServiceProviderRequest.builder()
                    .providerId(item.getProviderId())
                    .serviceId(item.getServiceId())
                    .build())
        .collect(Collectors.toList());
  }
}
