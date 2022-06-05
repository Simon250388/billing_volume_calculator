package org.billing.api.app.validator;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.billing.api.model.validator.ExistValueType;
import org.billing.api.model.validator.ValueExistChecker;
import org.billing.api.repository.AccountServiceDbService;
import org.billing.api.repository.ProviderDbService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderValueExistChecker implements ValueExistChecker {

  private final ProviderDbService providerDbService;

  @Override
  public boolean exist(String value) {
    return Optional.ofNullable(value).map(providerDbService::existsById).orElse(true);
  }

  @Override
  public ExistValueType getValueType() {
    return ExistValueType.PROVIDER;
  }
}
