package com.best.billingvolumecalculator.services.catalog;

import com.best.billingvolumecalculator.basemodels.BaseEntity;

import java.util.Optional;

public interface BaseEntityService<T extends BaseEntity> {
    T save(T accountingPoint);
    Optional<T> findById(long id);
}
