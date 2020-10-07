package com.best.billing.volumecalculator.services.catalog;

import com.best.billing.volumecalculator.basemodels.BaseEntity;
import com.best.billing.volumecalculator.dto.BaseEntityDTO;

import java.util.Optional;

public interface BaseEntityService<E extends BaseEntity, D extends BaseEntityDTO> {
    E save(E accountingPoint);
    Optional<D> findById(long id);
}
