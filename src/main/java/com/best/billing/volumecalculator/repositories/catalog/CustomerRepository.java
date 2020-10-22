package com.best.billing.volumecalculator.repositories.catalog;

import com.best.billing.volumecalculator.models.catalog.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
