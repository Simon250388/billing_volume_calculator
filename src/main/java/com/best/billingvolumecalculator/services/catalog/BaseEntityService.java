package com.best.billingvolumecalculator.services.catalog;

import com.best.billingvolumecalculator.basemodels.BaseEntity;
import com.best.billingvolumecalculator.dto.BaseEntityDTO;

import java.util.Optional;

public interface BaseEntityService<E extends BaseEntity, D extends BaseEntityDTO> {
    E save(E accountingPoint);
    Optional<D> findById(long id);
}
