package com.best.billingvolumecalculator.repositories.catalog;

import com.best.billingvolumecalculator.models.catalog.Meter;
import org.springframework.data.repository.CrudRepository;

public interface MeterRepository extends CrudRepository<Meter, Long> {
}
