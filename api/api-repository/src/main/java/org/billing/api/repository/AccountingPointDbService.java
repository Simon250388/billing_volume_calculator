package org.billing.api.repository;

import java.time.Instant;
import java.util.Optional;
import lombok.NonNull;
import org.billing.api.model.accountingPoint.AccountingPointRequest;
import org.billing.api.model.accountingPoint.AccountingPointResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingPointDbService {
  AccountingPointResponse save(String id, AccountingPointRequest request);

  Optional<AccountingPointResponse> findById(String id);

  void existOrElseThrow(@NonNull final String id);

  boolean notExistsById(String id);

  void saveHistory(AccountingPointRequest request, Instant instant);

  void deleteById(String id);
  void deleteAll();

  boolean exist(String value);
}
