package org.billing.calculation.repository;

import java.util.UUID;
import org.billing.calculation.model.CalculationResult;
import org.springframework.data.repository.CrudRepository;

public interface CalculationRepository extends CrudRepository<CalculationResult, UUID> {
}
