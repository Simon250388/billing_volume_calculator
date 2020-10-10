package com.best.billing.volumecalculator.services;

import com.best.billing.volumecalculator.models.BaseEntity;
import com.best.billing.volumecalculator.dto.BaseEntityDTO;

import java.util.Optional;

public interface BaseEntityService<E extends BaseEntity, D extends BaseEntityDTO> {
    D save(E accountingPoint);

    Optional<D> findById(long id);
}
