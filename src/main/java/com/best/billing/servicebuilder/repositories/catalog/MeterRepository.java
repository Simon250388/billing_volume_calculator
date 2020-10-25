package com.best.billing.servicebuilder.repositories.catalog;

import com.best.billing.servicebuilder.models.catalog.Meter;
import org.springframework.data.repository.CrudRepository;

public interface MeterRepository extends CrudRepository<Meter, Long> {
}
