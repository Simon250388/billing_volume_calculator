package com.best.billing.common.repository;

import com.best.billing.common.model.Meter;
import org.springframework.data.repository.CrudRepository;

public interface MeterRepository extends CrudRepository<Meter, Long> {
}
