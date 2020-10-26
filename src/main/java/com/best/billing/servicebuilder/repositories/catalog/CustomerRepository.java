package com.best.billing.servicebuilder.repositories.catalog;

import com.best.billing.common.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
