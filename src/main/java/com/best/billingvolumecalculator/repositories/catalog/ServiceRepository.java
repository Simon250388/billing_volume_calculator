package com.best.billingvolumecalculator.repositories.catalog;

import com.best.billingvolumecalculator.models.catalog.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}
