package com.best.billing.volumecalculator.repositories.catalog;

import com.best.billing.volumecalculator.models.catalog.Meter;
import org.springframework.data.repository.CrudRepository;

public interface MeterRepository extends CrudRepository<Meter, Long> {
}
