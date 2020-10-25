package com.best.billing.servicebuilder.repositories.catalog;

import com.best.billing.servicebuilder.models.catalog.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
