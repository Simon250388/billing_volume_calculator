package org.billing.api.repository;

import java.time.Instant;
import java.util.Optional;
import lombok.NonNull;
import org.billing.api.model.accountingPoint.AccountingPoint;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingPointDbService {
  AccountingPoint save(AccountingPoint request);

  Optional<AccountingPoint> findById(String id);

  void existOrElseThrow(@NonNull final String id);

  boolean notExistsById(String id);

  void saveHistory(AccountingPoint request, Instant instant);

  void deleteById(String id);
  void deleteAll();

  boolean exist(String value);
}
