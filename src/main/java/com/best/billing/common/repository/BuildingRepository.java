package com.best.billing.common.repository;

import com.best.billing.common.model.Building;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepository extends CrudRepository<Building, Long> {
    Iterable<Building> findByDescriptionContaining(String subStr);
}
