package com.best.billing.volumecalculator.services;

import com.best.billing.volumecalculator.dto.BaseEntityDTO;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface BaseEntityService<D extends BaseEntityDTO> {
    D save(@NotNull final D dto);

    Optional<D> findById(@NotNull final Long id);
}
