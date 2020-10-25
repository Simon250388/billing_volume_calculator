package com.best.billing.servicebuilder.services.historychange.implementation;

import com.best.billing.servicebuilder.dto.historychange.AccountingPointServiceProviderDTO;
import com.best.billing.servicebuilder.mappers.historychange.AccountingPointServiceProviderMapper;
import com.best.billing.servicebuilder.repositories.historychange.AccountingPointServiceProviderRepository;
import com.best.billing.servicebuilder.services.historychange.AccountingPointServiceProviderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountingPointServiceProviderServiceImpl implements AccountingPointServiceProviderService {
    private final AccountingPointServiceProviderRepository repository;
    private final AccountingPointServiceProviderMapper mapper;

    @Autowired
    public AccountingPointServiceProviderServiceImpl(AccountingPointServiceProviderRepository repository, AccountingPointServiceProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingPointServiceProviderDTO save(@NotNull AccountingPointServiceProviderDTO dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<AccountingPointServiceProviderDTO> findById(@NotNull Long id) {
        return repository.findById(id).map(mapper::fromEntity);
    }
}
