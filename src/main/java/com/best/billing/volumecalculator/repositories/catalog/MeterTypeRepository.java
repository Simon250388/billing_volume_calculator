package com.best.billing.volumecalculator.repositories.catalog;

import com.best.billing.volumecalculator.models.catalog.MeterType;
import org.springframework.data.repository.CrudRepository;

public interface MeterTypeRepository extends CrudRepository<MeterType, Long> {
}
