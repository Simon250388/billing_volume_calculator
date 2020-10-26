package com.best.billing.servicebuilder.repositories.catalog;

import com.best.billing.common.model.Building;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepository extends CrudRepository<Building, Long> {
    Iterable<Building> findByDescriptionContaining(String subStr);
}
