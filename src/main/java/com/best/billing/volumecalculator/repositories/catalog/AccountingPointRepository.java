package com.best.billing.volumecalculator.repositories.catalog;

import com.best.billing.volumecalculator.models.catalog.AccountingPoint;
import org.springframework.data.repository.CrudRepository;

public interface AccountingPointRepository  extends CrudRepository<AccountingPoint, Long> {
}
