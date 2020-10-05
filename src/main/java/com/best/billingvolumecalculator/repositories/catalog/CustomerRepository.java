package com.best.billingvolumecalculator.repositories.catalog;

import com.best.billingvolumecalculator.models.catalog.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
