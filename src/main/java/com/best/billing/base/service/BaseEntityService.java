package com.best.billing.base.service;

import com.best.billing.base.dto.BaseEntityDTO;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface BaseEntityService<D extends BaseEntityDTO> {
    D save(@NotNull final D dto);

    Optional<D> findById(@NotNull final Long id);
}
