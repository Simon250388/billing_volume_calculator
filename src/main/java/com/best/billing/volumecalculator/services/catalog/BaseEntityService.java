package com.best.billing.volumecalculator.services.catalog;

import com.best.billing.volumecalculator.basemodels.BaseEntity;

import java.util.Optional;

public interface BaseEntityService<T extends BaseEntity> {
    T save(T accountingPoint);
    Optional<T> findById(long id);
}
