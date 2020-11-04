package com.best.billing.base.service;

import com.best.billing.base.dto.BaseEntityDTO;
import lombok.NonNull;

import java.util.Optional;

public interface BaseEntityService<D extends BaseEntityDTO> {
    D save(@NonNull final D dto);

    Optional<D> findById(@NonNull final Long id);
}
