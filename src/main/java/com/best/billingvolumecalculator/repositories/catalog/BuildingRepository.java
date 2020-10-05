package com.best.billingvolumecalculator.repositories.catalog;

import com.best.billingvolumecalculator.models.catalog.Building;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepository extends CrudRepository<Building, Long> {
}
