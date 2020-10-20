package com.best.billing.volumecalculator.services.catalog.implementation;

import com.best.billing.volumecalculator.dto.catalog.AccountingPointDTO;
import com.best.billing.volumecalculator.mappers.catalog.AccountingPointMapper;
import com.best.billing.volumecalculator.repositories.catalog.AccountingPointRepository;
import com.best.billing.volumecalculator.services.BaseEntityService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountingPointServiceImpl implements BaseEntityService<AccountingPointDTO> {
    private final AccountingPointRepository repository;
    private final AccountingPointMapper mapper;

    @Autowired
    public AccountingPointServiceImpl(AccountingPointRepository repository, AccountingPointMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointDTO save(@NotNull final AccountingPointDTO dto) {
        return mapper.fromEntity(this.repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointDTO> findById(@NotNull final Long id) {
        return this.repository.findById(id).map(mapper::fromEntity);
    }
}
