package com.best.billing.volumecalculator.repositories.catalog;

import com.best.billing.volumecalculator.models.catalog.RateGroup;
import org.springframework.data.repository.CrudRepository;

public interface RateGroupRepository extends CrudRepository<RateGroup, Long> {
}
