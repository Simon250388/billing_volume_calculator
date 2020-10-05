package com.best.billingvolumecalculator.repositories.catalog;

import com.best.billingvolumecalculator.models.catalog.AccountingPoint;
import org.springframework.data.repository.CrudRepository;

public interface AccountingPointRepository  extends CrudRepository<AccountingPoint, Long> {
}
