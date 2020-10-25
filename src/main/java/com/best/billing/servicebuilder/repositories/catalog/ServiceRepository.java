package com.best.billing.servicebuilder.repositories.catalog;

import com.best.billing.servicebuilder.models.catalog.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}
