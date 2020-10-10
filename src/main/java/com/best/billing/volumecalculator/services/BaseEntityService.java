package com.best.billing.volumecalculator.services;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;

import java.util.Optional;

public interface BaseEntityService<D extends BaseEntityDTO> {
    D save(D entity);

    Optional<D> findById(long id);
}
