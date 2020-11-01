package com.best.billing.common.repository.catalog;

import com.best.billing.common.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}
