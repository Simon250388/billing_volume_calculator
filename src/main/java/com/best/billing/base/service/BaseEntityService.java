package com.best.billing.base.service;

import com.best.billing.base.model.BaseEntity;

import java.util.Optional;

public interface BaseEntityService<D extends BaseEntity> {
    D save(D entity);
    Optional<D> findById(Long id);
}
